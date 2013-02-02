	
	<!-- The bottom part of the navigation -->
	<div id="nav-bottom">
	</div>
	
	<img src="<?php bloginfo('stylesheet_directory'); ?>/layoutImages/cornfield.jpg">
	
	<div id="footer">
		<!-- Contains credit and copywrite information. -->
		<div class="padding-10">
			<?php
				$footer = new WP_Query('pagename=footer/');
				while($footer->have_posts()) : $footer->the_post();
					the_content();
				endwhile;
			?>
		</div>
	</div>
	
</div>

<script type="text/javascript" src="<?php bloginfo('stylesheet_directory'); ?>/script.js"></script>

<?php wp_footer(); ?>
</body>
</html>
