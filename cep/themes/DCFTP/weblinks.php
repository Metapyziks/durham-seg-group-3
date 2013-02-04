<?php /* Template Name: Weblinks Page */ ?>

<?php get_header(); ?>

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
										
				<?php endwhile; ?>
				
				<?php
					if(function_exists('print_weblinks')) {
						print_weblinks();
					}
				?>
				
				<div style="clear: both;"></div>
				
				<?php if ( comments_open() ) : ?>
					<?php comments_popup_link( ); ?>  |  <a href="#leaveComment">Leave a comment</a>
					<?php comments_template(); ?>
				<?php endif; ?>
			

		</div>
		<div class="main-margin">
		</div>
	</div>
	
	<?php get_footer(); ?>