<?php
	/*
	Plugin Name: Remove Website Field
	Description: Removes the website field from the comments form
	*/
	
	add_filter('comment_form_default_fields', 'url_filtered');
	function url_filtered($fields)
	{
	  if(isset($fields['url']))
	   unset($fields['url']);
	  return $fields;
	}
?>