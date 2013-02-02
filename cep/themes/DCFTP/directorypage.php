<?php /* Template Name: Directory Page */ ?>

<?php
	wp_register_script('google-maps-api', 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBsQ0syxq6tKXsyCAoef02pSbrr2PcP0Tw&sensor=false');
	wp_enqueue_script('google-maps-api');
	get_header();
?>

<div id="main-section">
	<div class="nav-margin">
	</div>
	
	<!-- Get the navigation links -->
	<?php get_sidebar(); ?>
	
	<div class="main-margin">
	</div>
	
	<div id="main">
		<!-- The content area; restricted width to leave space for sidebar. -->
	
		<div id="page-title">
			<?php single_post_title(); ?>
		</div>
			
		<!-- Get the page content -->
		<?php /* Start the Loop */ ?>
		<?php while ( have_posts() ) : the_post(); ?>
			<?php the_content( ); ?>
		<?php endwhile; ?>
		
		<?PHP
			$sql = "SELECT * FROM dcftp_directory ORDER BY name ASC";
			$data = $wpdb->get_results($sql, ARRAY_A);
		?>

		<!-- The map, with controls and search function -->
		
		<div style="text-align: center;">
			Enter an address or postcode: <input type="text" id="location" name="search" style="height: 22px;" />
			<input type="button" value="Find" onclick="moveToLocation()" class="button" />
		</div>

		<form id="hidden_locations">
			<?PHP
				foreach ($data as $entry) {
					$curString = 'false';
					if ($entry['outletID'] == $_GET['retailer_id']) {
						$curString = 'true';
					}
					echo '<input type="hidden" id="loc'.$entry['outletID'].'" value="'.$entry['latitude'].','.$entry['longitude'].','.$entry['name'].','.$curString.'" />';
				}
			?>
		</form>

		<div id="mapCanvas" style="margin-left: auto; margin-right: auto; margin-top: 16px; width:90%; height:320px; text-shadow: 0px 0px #000000;">
		</div>

		<?PHP display_entries($data); ?> 
	</div>
	
	<div class="main-margin">
	</div>
</div>

<?php get_footer(); ?>