<?php
/**
 * Directory Manager List Table class.
 *
 * @package Directory_Manager
 * @since 0.1
 */

if(!class_exists('WP_List_Table')) {
    require_once(ABSPATH.'wp-admin/includes/class-wp-list-table.php');
}

class WP_Directory_List_Table extends WP_List_Table
{
    function __construct() {
        global $status, $page;
                
        parent::__construct(array(
            'singular'  => 'retailer',
            'plural'    => 'retailers',
            'ajax'      => false
        ));
    }

    function starImages($rating) {
        $star = '<img src="/wp-content/plugins/directory/images/star.png" />';
        $grey = '<img src="/wp-content/plugins/directory/images/star_grey.png" />';

        return str_repeat($star, $rating).str_repeat($grey, 3 - $rating);
    }

    function actionButtons($item) {
        return
            '<a href="/?page_id=7&retailer_id='.$item['outletID'].'"><img title="View" src="/wp-content/plugins/directory/images/magnifier.png" /></a>&nbsp;'.
            '<img title="Edit" src="/wp-content/plugins/directory/images/pencil.png" />&nbsp;'.
            '<img title="Delete" src="/wp-content/plugins/directory/images/delete.png" />';
    }

    function column_default($item, $column_name) {
        switch($column_name){
            case 'name':
                return '<a href="'.$item['url'].'">'.$item[$column_name].'</a>';
            case 'postcode':
            case 'contact':
            case 'phone':
                return $item[$column_name];
            case 'location':
                return '<a href="https://maps.google.co.uk/maps?q='.$item['latitude'].','.$item['longitude'].'">'.$item['addressline1'].' '.$item['postcode'].'</a>'; 
            case 'stars':
                return $this->starImages($item[$column_name]);
            case 'action':
                return $this->actionButtons($item);
            default:
                return print_r($item,true);
        }
    }

    function column_cb($item) {
        return sprintf(
            '<input type="checkbox" name="%1$s[]" value="%2$s" />',
            /*$1%s*/ $this->_args['singular'],
            /*$2%s*/ $item['ID']
        );
    }

    function get_columns() {
        $columns = array(
            //'cb'            => '<input type="checkbox" />',
            'name'          => 'Retailer',
            'contact'       => 'Contact',
            'phone'         => 'Phone',
            'location'      => 'Location',
            'stars'         => 'Rating',
            'action'        => 'Action'
        );
        return $columns;
    }

    function get_sortable_columns() {
        $sortable_columns = array(
            'name'     => array('name', false),
            'stars'    => array('stars', false)
        );
        return $sortable_columns;
    }

    function prepare_items() {
        global $wpdb;

        $per_page = 10;
        
        $columns = $this->get_columns();
        $hidden = array();
        $sortable = $this->get_sortable_columns();
        
        $this->_column_headers = array($columns, $hidden, $sortable);
        
        $sql = "SELECT * FROM dcftp_directory";

        $data = $wpdb->get_results($sql, ARRAY_A);

        function usort_reorder($a,$b){
            $orderby = (!empty($_REQUEST['orderby'])) ? $_REQUEST['orderby'] : 'title';
            $order = (!empty($_REQUEST['order'])) ? $_REQUEST['order'] : 'asc';
            $result = strcmp($a[$orderby], $b[$orderby]);
            return ($order==='asc') ? $result : -$result;
        }
        usort($data, 'usort_reorder');
        
        $current_page = $this->get_pagenum();
        $total_items = count($data);
        
        $data = array_slice($data,(($current_page-1)*$per_page),$per_page);
        
        $this->items = $data;
        
        $this->set_pagination_args( array(
            'total_items' => $total_items,
            'per_page'    => $per_page,
            'total_pages' => ceil($total_items/$per_page)
        ) );
    }
}
