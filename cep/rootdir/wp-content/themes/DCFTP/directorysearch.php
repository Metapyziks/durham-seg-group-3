<?php /* Template Name: Full Directory */ ?>

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

			<div style="text-align: center;">
				<input id="searchName"></input><br />
				<button onclick="getMap()" class="button">Search</button>
			</div>	
			
			<!-- This should be dynamically generated, so that letters not used don't display -->
			
			<p style="text-align: center;">
				<a onclick="searchDatabase('number')">0 - 9</a> | 
				<a onclick="searchDatabase('a')">A</a> | 
				<a onclick="searchDatabase('b')">B</a> | 
				<!-- I got bored. -->
				C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z
			</p>
			
			<div id="entries">
				<!-- The search results would display here -->
			</div>
				
		</div>
		<div class="main-margin">
		</div>
	</div>
	
	<?php get_footer(); ?>