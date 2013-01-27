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
    add_menu_page('Directory Manager', 'Directory', 'publish_pages',
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
    if ( !current_user_can( 'publish_pages' ) )  {
        wp_die( __( 'You do not have sufficient permissions to access this page.' ) );
    }

    echo '<div class="wrap">';
    echo '<h2>Add New Retailer</h2>';
    echo '</div>';
}
?>
