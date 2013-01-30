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
    var $test_data = array(
        array(
            'id' => 1,
            'name' => 'Oxfam Bookshop',
            'postcode' => 'DH1 3AA',
            'website' => 'http://www.oxfam.org.uk/shop/local-shops/oxfam-books-music-durham',
            'contact' => 'Michael Ridsdale',
            'phone' => '0191 3846366',
            'location' => '54.775122,-1.574381',
            'stars' => 3
        ),
        array(
            'id' => 2,
            'name' => 'Oxfam Boutique',
            'postcode' => 'DH1 3AA',
            'website' => 'http://www.oxfam.org.uk/shop/local-shops/oxfam-boutique-durham',
            'contact' => '-',
            'phone' => '0191 3847440',
            'location' => '54.778102,-1.573104',
            'stars' => 2
        )
    );

    function __construct() {
        global $status, $page;
                
        parent::__construct(array(
            'singular'  => 'retailer',
            'plural'    => 'retailers',
            'ajax'      => false
        ));
    }

    function column_default($item, $column_name) {
        switch($column_name){
            case 'name':
                return '<a href="'.$item['website'].'">'.$item[$column_name].'</a>';
            case 'postcode':
            case 'contact':
            case 'phone':
                return $item[$column_name];
            case 'location':
                return '<a href="https://maps.google.co.uk/maps?q='.$item[$column_name].'">View</a>'; 
            case 'stars':
                return str_repeat('<img src="/wp-content/plugins/directory/images/star.png" />', $item[$column_name])
                    .str_repeat('<img src="/wp-content/plugins/directory/images/star_grey.png" />', 3 - $item[$column_name]);
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
            'postcode'      => 'Post Code',
            'contact'       => 'Contact',
            'phone'         => 'Phone&nbsp;Number',
            'stars'        => 'Star&nbsp;Rating',
            'location'      => 'Location'
        );
        return $columns;
    }

    function get_sortable_columns() {
        $sortable_columns = array(
            'name'     => array('name',false),
            'stars'    => array('stars',false)
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
        
        $data = $this->test_data;

        function usort_reorder($a,$b){
            $orderby = (!empty($_REQUEST['orderby'])) ? $_REQUEST['orderby'] : 'title';
            $order = (!empty($_REQUEST['order'])) ? $_REQUEST['order'] : 'asc';
            $result = strcmp($a[$orderby], $b[$orderby]);
            return ($order==='asc') ? $result : -$result;
        }
        usort($data, 'usort_reorder');
        
        
        /***********************************************************************
         * ---------------------------------------------------------------------
         * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
         * 
         * In a real-world situation, this is where you would place your query.
         * 
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         * ---------------------------------------------------------------------
         **********************************************************************/
        
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
