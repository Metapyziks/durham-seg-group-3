<?php /* Template Name: View PDF */ ?>

<?php get_header(); ?>

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
				
				<!-- Comments are permanently disabled on the homepage -->
				
			<?php endwhile; ?>
			
		<a href="file:///C:/xampp-portable/htdocs/wordpress/wp-content/themes/DCFTP/cdftd_webready.pdf">
			<img src="http://localhost/wp-content/uploads/2013/01/cdftd_web.gif">
		</a>
			
	</div>
	
	<div class="main-margin">
	</div>
</div>
	
<?php get_footer(); ?>