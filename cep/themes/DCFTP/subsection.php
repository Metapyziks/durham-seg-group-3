<?php
/* Template Name: Subsection */
 
 get_header(); ?>
 
 <script type="text/javascript"> 
	// If the page is loaded directly, redirect to the home page.
	
	if (window.top.location == window.location) {   
		window.location.replace("<?php echo bloginfo('siteurl');?>"); 
	} 
</script>

<div id="main-section">
	<div class="nav-margin">
	</div>
	
	<?php get_sidebar(); ?>
	
	<div class="main-margin">
		</div>
		<div id="main">
			<div id="page-title">
				<?php single_post_title(); ?>
			</div>
			<?php /* Start the Loop */ ?>
				<?php while ( have_posts() ) : the_post(); ?>
					<?php the_content( ); ?>
					
					<?php if ( comments_open() ) : ?>
						<?php comments_popup_link( ); ?>  |  <a href="#leaveComment">Leave a comment</a>
						<?php comments_template(); ?>
					<?php endif; ?>
					
				<?php endwhile; ?>

		</div>
		<div class="main-margin">
		</div>
	</div>
	
	<?php get_footer(); ?>