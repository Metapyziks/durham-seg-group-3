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

add_action('admin_menu', 'directory_manager_menu');

function directory_manager_menu() {
    add_menu_page('Add Item', 'Directory', 'manage_options',
        'dirman-add-item', 'directory_manager_add_item');
}

function directory_manager_add_item() {
    if ( !current_user_can( 'manage_options' ) )  {
        wp_die( __( 'You do not have sufficient permissions to access this page.' ) );
    }
    echo '<div class="wrap">';
    echo '<p>Here is where the form would go if I actually had options.</p>';
    echo '</div>';
}
?>
