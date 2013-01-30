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
}

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
    $wp_list_table->views();
    $wp_list_table->display();
    echo '</div>';
}

function directory_manager_add_item() {
    if (!current_user_can('publish_pages')) {
        wp_die(__('You do not have sufficient permissions to access this page.'));
    }

    global $wpdb;

    wp_register_script('google-maps-api', 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBsQ0syxq6tKXsyCAoef02pSbrr2PcP0Tw&sensor=false');
    wp_register_script('add-retailer', '/wp-content/plugins/directory/addretailer.js');
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
        $rating = 2;
        $city = 'Durham';
        $county = 'County Durham';
    }
?>
    <div class="wrap">
    <h2>Add New Retailer</h2>
    <form name="addretailer" action="admin.php?page=directory-manager-add" method="post" onload="initialize()">
    <input type="hidden" value="1" name="submitted" />
    <input type="hidden" value="0.0" id="latitude" name="latitude" />
    <input type="hidden" value="0.0" id="longitude" name="longitude" />
    <table border="0">
    <tr>
    <td align="right">Name:</td><td><input type="text" name="name" value="<?PHP echo $name; ?>"/></td><td>&nbsp;</td>
    <td align="right">Address Line 1:</td><td><input type="text" id="addressline1" name="addressline1" value="<?PHP echo $addressline1; ?>"/></td>
    <td>&nbsp;</td><td><input type="button" value="Find Address" onclick="findAddress()"/></td>
    </tr>
    <tr>
    <td align="right">Contact:</td><td><input type="text" name="contact" value="<?PHP echo $contact; ?>"/></td><td>&nbsp;</td>
    <td align="right">Address Line 2:</td><td><input type="text" id="addressline2" name="addressline2" value="<?PHP echo $addressline2; ?>"/></td>
    <td>&nbsp;</td><td rowspan="5"><div id="mapCanvas" style="width:384px; height:320px; text-shadow: 0px 0px #000000;"></div></td>
    </tr>
    <tr>
    <td align="right">Phone No:</td><td><input type="text" name="phone" value="<?PHP echo $phone; ?>"/></td><td>&nbsp;</td>
    <td align="right">City:</td><td><input type="text" id="city" name="city" value="<?PHP echo $city; ?>"/></td>
    </tr>
    <tr>
    <td align="right">Website:</td><td><input type="text" name="website" value="<?PHP echo $website; ?>"/></td><td>&nbsp;</td>
    <td align="right">County:</td><td><input type="text" id="county" name="county" value="<?PHP echo $county; ?>" /></td>
    </tr>
    <tr>
    <td align="right">Rating:</td><td><input type="range" min="1" max="3" name="stars" value="<?PHP echo $stars; ?>"/></td><td>&nbsp;</td>
    <td align="right">Post Code:</td><td><input type="text" id="postcode" name="postcode" value="<?PHP echo $postcode; ?>"/></td>
    </tr>
    <tr>
    <td valign="top" align="right">Information:</td><td colspan="4"><textarea style="width:100%;" rows="12" name="information"><?PHP echo $information; ?></textarea></td>
    </tr>
    <tr><td /><td colspan="3"><?PHP echo $message; ?></td>
    <td><input type="submit" style="width:100%;" value="Add Retailer" /></td>
    </tr>
    </table>
    </form>
    </div>
<?PHP
}
?>
