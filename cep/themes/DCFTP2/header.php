<!DOCTYPE html>

<?php
    function star_images($rating) {
        $star = '<img src="/wp-content/plugins/directory/images/star.png" />';
        $grey = '<img src="/wp-content/plugins/directory/images/star_grey.png" />';

        return str_repeat($star, $rating).str_repeat($grey, 3 - $rating);
    }

	function display_entries($data)
	{
		echo '<div id="entries">';
		foreach ($data as $entry) {
			echo '<a name="entry'.$entry['outletID'].'"></a>';
			echo '<div class="entry" id="entry'.$entry['outletID'].'">';
			echo '<div class="entry-left">';
			echo '<div class="entry-name">';
			echo '<div class="entry-rating">';
			echo star_images($entry['stars']);
			echo '</div>';
			echo $entry['name'];
			echo '</div>';
			echo '<p>';
			echo $entry['information'];
			echo '</p>';
			echo '</div>';
			echo '<div class="entry-right">';
			echo '<p>';
			echo $entry['addressline1'].'<br />';
			echo $entry['addressline2'].'<br />';
			echo $entry['city'].'<br />';
			echo $entry['postcode'];
			echo '</p>';
			echo '<p>';
			echo $entry['phone'];
			echo '</p>';
			echo '<p>';
			echo '<a href="'.$entry['url'].'">Website</a>';
			echo '</p>';
			echo '</div>';
			echo '</div>';
		}
		echo '</div>';
	}
?>

<html>
<head>
	<title><?php
	/*
	 * Print the <title> tag based on what is being viewed.
	 */
	global $page, $paged;

	wp_title( '|', true, 'right' );

	// Add the blog name.
	bloginfo( 'name' );

	// Add the blog description for the home/front page.
	$site_description = get_bloginfo( 'description', 'display' );
	if ( $site_description && ( is_home() || is_front_page() ) )
		echo " | $site_description";

	// Add a page number if necessary:
	if ( $paged >= 2 || $page >= 2 )
		echo ' | ' . sprintf( __( 'Page %s', 'twentyeleven' ), max( $paged, $page ) );

	?></title>
	
	<link rel="stylesheet" type="text/css" media="all" href="<?php bloginfo( 'stylesheet_url' ); ?>" />
	
	<!-- Get the favicon -->
	<link rel="shortcut icon" type="image/x-icon" href="<?php bloginfo('stylesheet_directory'); ?>/favicon.ico">
	<!-- for IE7 -->
	<link rel="shortcut icon" href="<?php bloginfo('stylesheet_directory'); ?>/favicon.ico">
	<link rel="icon" type="image/ico" href="<?php bloginfo('stylesheet_directory'); ?>/favicon.ico">
	
	<!-- Enable threaded comments -->
	<?php if ( is_page() ) wp_enqueue_script( 'comment-reply' ); ?>
	
	<?php wp_head(); ?>
</head>

<body <?php body_class(); ?>>
<div id="wrapper">
	<div id="header">
		<div class="header-image-landscape-wrapper">
			<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/Sky.jpg">
		</div>
		<div class="header-image-portrait">
			<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/cotton-man.jpg">
		</div>
		<div class="header-image-landscape-wrapper">
			<div class="header-image-landscape">
				<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/olive-men.jpg">
			</div>
			<div class="header-image-landscape">
				<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/coffee-woman.jpg">
			</div>
		</div>
		<div class="header-image-portrait">
			<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/coffee-man.jpg">
		</div>
		<div class="header-image-landscape-wrapper">
			<div class="header-image-landscape">
				<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/banana-man.jpg">
			</div>
			<div class="header-image-landscape">
				<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/tea-woman.jpg">
			</div>
		</div>
		<?php wp_head(); ?>
	</div>
	<div id="site-title">
		<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/title.png">
	</div>
	<div id="nav-top">
		<div class="padding-10">
			<!-- Logo goes here -->
			<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/logo.jpg">
		</div>
	</div>