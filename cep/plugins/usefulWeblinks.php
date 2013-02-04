<?php
	/*
		Plugin Name: Manage Weblinks
		Description: Manage useful weblinks and descriptions to print to the "Useful weblinks" page
		Version: 1.0
		Author: Emma Nugee
		License: GPL
	*/

	/* The function used to display the weblinks on the website */
	function print_weblinks()
	{
		$query="SELECT * FROM wp_weblinks_table";
		$result=mysql_query($query);
		
		$num=mysql_numrows($result);
		
		$i=0;
		while ($i < $num) {

		$f1=mysql_result($result,$i,"name");
		$f2=mysql_result($result,$i,"url");
		$f3=mysql_result($result,$i,"description");
		?>
		
		<div class="weblink">
			<div class="weblink-padding">
				<a href="<?php echo $f2; ?>"><?php echo $f1; ?></a>
				<p>
					<?php echo $f3; ?>
				</p>
			</div>
		</div>

		<?php
		$i++;
		}

	}
	
	/*	-----------------------------------------------------------------------
		Setting up the database, and calling it when the plugin activates.
		----------------------------------------------------------------------- */
	
	global $jal_db_version;
	$jal_db_version = "1.0";

	function jal_install() {
	   global $jal_db_version;
		  
	   $sql = "CREATE TABLE wp_weblinks_table (
			id mediumint(9) NOT NULL AUTO_INCREMENT,
			name text NOT NULL,
			url text NOT NULL,
			description text NOT NULL,
			UNIQUE KEY id (id)
		);";

	   require_once(ABSPATH . 'wp-admin/includes/upgrade.php');
	   dbDelta($sql);
	 
	   add_option("jal_db_version", $jal_db_version);
	}

	register_activation_hook(__FILE__,'jal_install');
	
	/*	-----------------------------------------------------------------------
		Creating the settings page
		----------------------------------------------------------------------- */
	
	// Hook for adding admin menus
	add_action('admin_menu', 'weblinks_add_menu');

	// action function for above hook
	function weblinks_add_menu() {
		// Add a new top-level menu
		add_menu_page(__('Weblinks','useful-weblinks'), __('Weblinks','useful-weblinks'), 'manage_options', 'manage-useful-weblinks', 'weblinks_html' );
	}

	// weblinks_html() displays the page content for the settings page
	function weblinks_html() {
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

			mysql_query($sql) 
			or die(mysql_error());  

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
		
		<style>
			.weblink {
				width: 45%;
				margin: 10px;
				border: 1px solid #000;
				background: #ccc;
				overflow: auto; /* used to clear the floats */
				float: left;
			}
			
			.weblink-padding {
				margin: 10px;
			}
			
			.weblink a, .weblink a:active, .weblink a:visited {
				width: 100%;
				height: 20px;
				line-height: 20px;
				font: 14pt georgia;
				display: block;
				text-decoration: none;
				color: #444;
				border-bottom: 1px dotted #444;
			}
			.weblink a:hover {
				border-bottom: 1px solid #000;
				color: #000;
			}
		</style>

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
	</td></tr></table>

	</form>
	<?php
	
		print_weblinks();
		echo '</div>';
 
	}
?>