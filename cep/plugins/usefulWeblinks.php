<?php
	/*
		Plugin Name: Manage Weblinks
		Description: Manage useful weblinks and descriptions to print to the "Useful weblinks" page
		Version: 1.0
		Author: Emma Nugee
		License: GPL
	*/

	/* The function used to display the weblinks on the website */
	function print_weblinks() {
		$query="SELECT * FROM wp_weblinks_table";
		$result=mysql_query($query);
		
		$num=mysql_numrows($result);		
		echo '<table style="width: 700px;">';
	
		$i=0;
		$even=true;
		while ($i < $num) {

			$f1=mysql_result($result,$i,"name");
			$f2=mysql_result($result,$i,"url");
			$f3=mysql_result($result,$i,"description");
			$f4=mysql_result($result,$i,"id");
			
			if ($even) {
				echo '<tr>';
			}
			?>
			<td style="width: 350px;">
			<div class="weblink">
				<div class="weblink-padding">
					<a href="<?php echo $f2; ?>"><?php echo $f1; ?></a>
					<p>
						<?php echo $f3; ?>						
					</p>
				</div>
			</div>
			</td>

			<?php
			$i++;
			
			if(!$even) {
				echo '</tr>';
				$even = true;
			}
			else {
				$even = false;
			}
		}
		echo '</table>';
	}
		
	function admin_print_weblinks() {
		$query="SELECT * FROM wp_weblinks_table";
		$result=mysql_query($query);
		
		$num=mysql_numrows($result);		
		echo '<table style="margin-top: 40px;">';
	
		$i=0;
		$even=true;
		while ($i < $num) {

			$f1=mysql_result($result,$i,"name");
			$f2=mysql_result($result,$i,"url");
			$f3=mysql_result($result,$i,"description");
			$f4=mysql_result($result,$i,"id");
			
			if ($even) {
				echo '<tr>';
			}
			?>
			<td style="width: 350px;">
			<div class="weblink">
				<div class="weblink-padding">
					<div class="big-link">
					<a href="<?php echo $f2; ?>"><?php echo $f1; ?></a>
					</div>
					<p>
						<?php echo $f3; ?>						
					</p>
					<p>
						<a href="admin.php?page=manage-useful-weblinks&action=delete&id=<?php echo $f4; ?>" class="delete-weblink">
							Delete weblink
						</a>
					</div>
			</div>
			</td>

			<?php
			$i++;
			
			if(!$even) {
				echo '</tr>';
				$even = true;
			}
			else {
				$even = false;
			}
		}
		echo '</table>';
	}
	
	/*	-----------------------------------------------------------------------
		Setting up the database, and calling it when the plugin activates.
		----------------------------------------------------------------------- */

	function jal_install() {
		  
	   $sql = "CREATE TABLE wp_weblinks_table (
			id mediumint(9) NOT NULL AUTO_INCREMENT,
			name text NOT NULL,
			url text NOT NULL,
			description text NOT NULL,
			UNIQUE KEY id (id)
		);";

	   require_once(ABSPATH . 'wp-admin/includes/upgrade.php');
	   dbDelta($sql);
	}

	register_activation_hook(__FILE__,'jal_install');
	
	/*	-----------------------------------------------------------------------
		Creating the settings page
		----------------------------------------------------------------------- */
	
	// Hook for adding admin menus
	add_action('admin_menu', 'weblinks_add_menu');

	// action function for above hook
	function weblinks_add_menu() {
		add_menu_page('Weblink Manager', 'Weblinks', 'manage_options',
			'manage-useful-weblinks', 'weblinks_html');
		add_submenu_page('manage-useful-weblinks', 'Weblink Manager - Add Weblink',
			'Add New Weblink', 'publish_pages', 'manage-weblinks-add',
			'weblinks_add_link');
		add_submenu_page(null, 'Useful Weblinks - Delete Weblink',
			'Delete Weblink', 'manage_options', 'useful-weblinks-delete',
			'useful_weblinks_delete_link');
	}

	// weblinks_html() displays the page content for the settings page
	function weblinks_add_link() {
		
		global $wpdb;
	
		//must check that the user has the required capability 
		if (!current_user_can('manage_options'))
		{
		  wp_die( __('You do not have sufficient permissions to access this page.') );
		}


		// variables for the field and option names 
		$hidden_field_name = 'mt_submit_hidden';
		
		$data_field_name_website_name = 'useful_weblinks_website_name';
		$data_field_name_website_url = 'useful_weblinks_website_url';
		$data_field_name_website_description = 'useful_weblinks_website_description';

		// See if the user has posted us some information
		// If they did, this hidden field will be set to 'Y'
		if( isset($_POST[ $hidden_field_name ]) && $_POST[ $hidden_field_name ] == 'Y' ) {
						
			// Insert a row of information into the table
			
			$sql="INSERT INTO wp_weblinks_table (name, url, description)
			VALUES
			('$_POST[$data_field_name_website_name]','$_POST[$data_field_name_website_name]','$_POST[$data_field_name_website_description]')";
			
			$wpdb->query($wpdb->prepare($sql));
			
			
			// Put an settings updated message on the screen
	?>
	<div class="updated"><p><strong><?php _e('Weblink added.', 'useful-weblinks' ); ?></strong></p></div>
	<?php
		}

		// Now display the settings editing screen
		echo '<div class="wrap">';
		echo "<h2>" . __( 'Manage Useful Weblinks', 'useful-weblinks' ) . "</h2>";
		
		// settings form
		?>

	<form name="form1" method="post" action="">
	<input type="hidden" name="<?php echo $hidden_field_name; ?>" value="Y">
	
	<table><tr><td width="130">

	<?php _e("Website name:", 'useful-weblinks' ); ?> 
	
	</td><td width="300">
	<input type="text" name="<?php echo $data_field_name_website_name; ?>" value="" style="width: 100%;">
	</td></tr>
	<tr><td>	
	<?php _e("Website address:", 'useful-weblinks' ); ?> 
	</td><td>
	<input type="text" name="<?php echo $data_field_name_website_url; ?>" value="" style="width: 100%;">
	</td></tr>
	<tr><td valign="top">
	<?php _e("Website description:", 'useful-weblinks' ); ?> 
	</td><td>
	<textarea name="<?php echo $data_field_name_website_description; ?>" style="width: 100%;" rows="5"></textarea>
	</td></tr>
	<tr><td colspan="2">
	<p class="submit">
	<input type="submit" name="Submit" class="button-primary" value="<?php esc_attr_e('Save Changes') ?>" />
	</p>
	</td></tr>
	</table>

	</form>
	<?php
	}
	function weblinks_html() {
	
	global $wpdb;
		
		if( isset($_GET['action']) && $_GET['action'] == 'delete' ) {
		
			$sql = "SELECT * FROM wp_weblinks_table WHERE id = '".$_GET['id']."'";
			
			$data = $wpdb->get_results($sql, ARRAY_A);
			if (count($data) == 0) {
				wp_die(__( 'Can\'t delete directory entry, entry does not exist!'.$_GET['id'] ));
			}

			$data = $data[0];
			$sql = "DELETE FROM wp_weblinks_table WHERE id = '".$_GET['id']."'";
			$wpdb->query($wpdb->prepare($sql));  
		}
		
		?>
		<style>
			.weblink {
				width: 90%;
				margin: 10px;
				border: 1px solid #000;
				background: #ccc;
				overflow: auto; /* used to clear the floats */
				float: left;
			}
			
			.weblink-padding {
				margin: 10px;
			}
			
			.big-link a, .big-link a:active, .big-link a:visited {
				width: 100%;
				line-height: 20px;
				font: 14pt georgia;
				display: block;
				text-decoration: none;
				color: #444;
				border-bottom: 1px dotted #444;
			}
			.big-link a:hover {
				border-bottom: 1px solid #000;
				color: #000;
			}
		</style>
		
		<h2>Manage Useful Weblinks</h2>
		
		<p>
			Here you can see how the weblinks would appear on your website (the delete button, of course, is only visible here). Adding a new weblink is available from the form under "And New Weblink" to the left.
		</p>
		
		<?php
		admin_print_weblinks();
		echo '</div>';
		echo '<div style="clear: both;"></div>';
 
	}
	
	function useful_weblinks_delete_link() {
		global $wpdb;
		
		$sql = "SELECT * FROM wp_weblinks_table WHERE id = '".$_GET['id']."'";
		
		$data = $wpdb->get_results($sql, ARRAY_A);
		if (count($data) == 0) {
			wp_die(__( 'Can\'t delete directory entry, entry does not exist!'.$_GET['id'] ));
		}

		$data = $data[0];
        $sql = "DELETE FROM wp_weblinks_table WHERE id = '".$_GET['id']."'";
		$wpdb->query($wpdb->prepare($sql));  
    }
?>