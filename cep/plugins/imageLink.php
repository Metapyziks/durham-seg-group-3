<?php
	/*
	Plugin Name: Remove default image links
	Description: Stops images being inserted as links by default.
	*/

	update_option('image_default_align', 'none' );
	update_option('image_default_link_type', 'none' );
	update_option('image_default_size', 'large' );

?>