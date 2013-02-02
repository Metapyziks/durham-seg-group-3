<?php
/**
 * @package Directory_Manager
 * @version 0.1
 */
/*
Plugin Name: Directory Manager
Author: James King
Version: 0.1
*/

require_once('wp-class-directory-list-table.php');

add_action('admin_menu', 'directory_manager_menu');

function directory_manager_menu() {
    add_menu_page('Directory Manager', 'DCFTP Directory', 'publish_pages',
        'directory-manager', 'directory_manager_main');
    add_submenu_page('directory-manager', 'Directory Manager - Add New Retailer',
        'Add New Retailer', 'publish_pages', 'directory-manager-add',
        'directory_manager_add_item');
    add_submenu_page(null, 'Directory Manager - Edit Retailer',
        'Edit Retailer', 'publish_pages', 'directory-manager-edit',
        'directory_manager_edit_item');
    add_submenu_page(null, 'Directory Manager - Delete Retailer',
        'Delete Retailer', 'publish_pages', 'directory-manager-delete',
        'directory_manager_delete_item');
}

function directory_admin_header() {
    $page = ( isset($_GET['page'] ) ) ? esc_attr( $_GET['page'] ) : false;
    if ('directory-manager' != $page) return; 

    echo '<style type="text/css">';
    echo '.wp-list-table .column-phone { width: 96px; }';
    echo '.wp-list-table .column-stars { width: 64px; }';
    echo '.wp-list-table .column-action { width: 64px; }';
    echo '</style>';
}

add_action('admin_head', 'directory_admin_header');

function directory_manager_main() {
    if ( !current_user_can( 'publish_pages' ) )  {
        wp_die( __( 'You do not have sufficient permissions to access this page.' ) );
    }
    echo '<div class="wrap">';

    $wp_list_table = new WP_Directory_List_Table();
    $pagenum = $wp_list_table->get_pagenum();
    $add_new_page = 'admin.php?page=directory-manager-add';

    $wp_list_table->prepare_items();

    echo '<div class="wrap">';
    echo '<h2>Directory Manager';
    echo ' <a href="'.esc_url($add_new_page).'" class="add-new-h2">Add New</a>';
    echo '</h2>';
    $wp_list_table->display();
    echo '</div>';
}

function directory_manager_form_header($retailer_id) {
    global $wpdb;

    wp_register_script('google-maps-api', 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBsQ0syxq6tKXsyCAoef02pSbrr2PcP0Tw&sensor=false');
    wp_register_script('add-retailer', '/wp-content/themes/DCFTP/script.js');
    wp_enqueue_script('google-maps-api');
    wp_enqueue_script('add-retailer');

    $message = '';
    if ($_POST['submitted']) {
        $name = $_POST['name'];
        $contact = $_POST['contact'];
        $phone = $_POST['phone'];
        $url = $_POST['website'];
        $stars = $_POST['stars'];
        $addressline1 = $_POST['addressline1'];
        $addressline2 = $_POST['addressline2'];
        $city = $_POST['city'];
        $county = $_POST['county'];
        $postcode = $_POST['postcode'];
        $information = $_POST['information'];
        $latitude = $_POST['latitude'];
        $longitude = $_POST['longitude'];

        if (!$name) {
            $message = 'Please enter a name';
        } elseif ($retailer_id) {
            $sql = "UPDATE dcftp_directory SET
                name='".$name."', contact='".$contact."', phone='".$phone."', url='".$url."', stars='".$stars."',
                addressline1='".$addressline1."', addressline2='".$addressline2."', city='".$city."', county='".$county."', postcode='".$postcode."',
                information='".$information."', latitude='".$latitude."', longitude='".$longitude."'
            WHERE outletID = '".$retailer_id."'";
            
            $wpdb->query($sql);

            $message = 'Updated entry for '.$name.'!';
        } else {
            $sql = "INSERT INTO dcftp_directory (
                name, contact, phone, url, stars,
                addressline1, addressline2, city, county, postcode,
                information, latitude, longitude
            ) VALUES (
                '".$name."', '".$contact."', '".$phone."', '".$url."', '".$stars."',
                '".$addressline1."', '".$addressline2."', '".$city."', '".$county."', '".$postcode."',
                '".$information."', '".$latitude."', '".$longitude."'
            )";
            
            $wpdb->query($sql);

            $message = 'Added '.$name.' to the directory!';
            
            $name = '';
            $contact = '';
            $phone = '';
            $url = '';
            $stars = '';
            $addressline1 = '';
            $addressline2 = '';
            $postcode = '';
            $information = '';
        }
    } else {
        if ($retailer_id) {
            $sql = "SELECT * FROM dcftp_directory WHERE outletID = '".$retailer_id."'";
            $data = $wpdb->get_results($sql, ARRAY_A);
            if (count($data) == 0) {
                wp_die(__( 'Can\'t edit directory entry, entry does not exist!' ));
            }
            return $data[0];
        } else {
            $rating = 2;
            $city = 'Durham';
            $county = 'County Durham';
            $latitude = 54.776842;
            $longitude = -1.575454;
        }
    }

    return array(
        "name" => $name,
        "contact" => $contact,
        "phone" => $phone,
        "url" => $url,
        "stars" => $stars,
        "addressline1" => $addressline1,
        "addressline2" => $addressline2,
        "city" => $city,
        "county" => $county,
        "postcode" => $postcode,
        "information" => $information,
        "latitude" => $latitude,
        "longitude" => $longitude,
        "message" => $message
    );
}

function directory_manager_construct_form($entry, $submitMessage) {
?>
    <form name="addretailer" action="admin.php?page=<?PHP echo $_GET['page']; if ($_GET['retailer_id']) echo '&retailer_id='.$_GET['retailer_id']; ?>" method="post">
    <input type="hidden" value="1" name="submitted" />
    <input type="hidden" value="<?PHP echo $entry['latitude']; ?>" id="latitude" name="latitude" />
    <input type="hidden" value="<?PHP echo $entry['longitude']; ?>" id="longitude" name="longitude" />
    <table border="0">
    <tr>
    <td align="right">Name:</td><td><input type="text" name="name" value="<?PHP echo $entry['name']; ?>"/></td><td>&nbsp;</td>
    <td align="right">Address Line 1:</td><td><input type="text" id="addressline1" name="addressline1" value="<?PHP echo $entry['addressline1']; ?>"/></td>
    <td>&nbsp;</td><td><input type="button" value="Find Address" onclick="findAddress()"/></td>
    </tr>
    <tr>
    <td align="right">Contact:</td><td><input type="text" name="contact" value="<?PHP echo $entry['contact']; ?>"/></td><td>&nbsp;</td>
    <td align="right">Address Line 2:</td><td><input type="text" id="addressline2" name="addressline2" value="<?PHP echo $entry['addressline2']; ?>"/></td>
    <td>&nbsp;</td><td rowspan="5"><div id="mapCanvas" style="width:384px; height:320px; text-shadow: 0px 0px #000000;"></div></td>
    </tr>
    <tr>
    <td align="right">Phone No:</td><td><input type="text" name="phone" value="<?PHP echo $entry['phone']; ?>"/></td><td>&nbsp;</td>
    <td align="right">City:</td><td><input type="text" id="city" name="city" value="<?PHP echo $entry['city']; ?>"/></td>
    </tr>
    <tr>
    <td align="right">Website:</td><td><input type="text" name="website" value="<?PHP echo $entry['url']; ?>"/></td><td>&nbsp;</td>
    <td align="right">County:</td><td><input type="text" id="county" name="county" value="<?PHP echo $entry['county']; ?>" /></td>
    </tr>
    <tr>
    <td align="right">Rating:</td><td><input type="range" min="1" max="3" name="stars" value="<?PHP echo $entry['stars']; ?>"/></td><td>&nbsp;</td>
    <td align="right">Post Code:</td><td><input type="text" id="postcode" name="postcode" value="<?PHP echo $entry['postcode']; ?>"/></td>
    </tr>
    <tr>
    <td valign="top" align="right">Information:</td><td colspan="4"><textarea style="width:100%;" rows="12" name="information"><?PHP echo $entry['information']; ?></textarea></td>
    </tr>
    <tr><td /><td colspan="3"><?PHP echo $entry['message']; ?></td>
    <td><input type="submit" style="width:100%;" value="<?PHP echo $submitMessage; ?>" /></td>
    </tr>
    </table>
    </form>
<?PHP
}

function directory_manager_add_item() {
    if (!current_user_can('publish_pages')) {
        wp_die(__('You do not have sufficient permissions to access this page.'));
    }
    
    $entry = directory_manager_form_header(null);

    echo '<div class="wrap">';
    echo '<h2>Add New Retailer</h2>';

    directory_manager_construct_form($entry, 'Add Retailer');
    
    echo '</div>';
}

function directory_manager_edit_item() {
    if (!current_user_can('publish_pages')) {
        wp_die(__('You do not have sufficient permissions to access this page.'));
    }

    $entry = directory_manager_form_header($_GET['retailer_id']);

    echo '<div class="wrap">';
    echo '<h2>Edit Retailer</h2>';

    directory_manager_construct_form($entry, 'Update Retailer');

    echo '</div>';
}

function directory_manager_delete_item() {
    global $wpdb;

    if (!current_user_can('publish_pages')) {
        wp_die(__('You do not have sufficient permissions to access this page.'));
    }

    $sql = "SELECT * FROM dcftp_directory WHERE outletID = '".$_GET['retailer_id']."'";
    $data = $wpdb->get_results($sql, ARRAY_A);
    if (count($data) == 0) {
        wp_die(__( 'Can\'t delete directory entry, entry does not exist!' ));
    }

    $data = $data[0];

    echo '<div class="wrap">';
    echo '<h2>Delete Retailer</h2>';

    if ($_POST['submitted']) {
        $sql = "DELETE FROM dcftp_directory WHERE outletID = '".$_GET['retailer_id']."'";
        $wpdb->query($sql);
        
        echo '<p>Deleted '.$data['name'].' from the directory!</p>';
        echo '<a href="admin.php?page=directory-manager">Back to manager</a>';
    } else {
        echo '<p><b>Are you sure you want to permanently delete '.$data['name'].' from the directory?</b></p>';
        echo '<form name="deleteretailer" action="admin.php?page='.$_GET['page'].'&retailer_id='.$_GET['retailer_id'].'" method="post">';
        echo '<input type="hidden" value="1" name="submitted" />';
        echo '<input type="submit" value="Delete" />';
        echo '<input type="button" value="Cancel" onclick="window.location.href=\'admin.php?page=directory-manager\';" />';
        echo '</form>';
    }
    echo '</div>';
}
?>
