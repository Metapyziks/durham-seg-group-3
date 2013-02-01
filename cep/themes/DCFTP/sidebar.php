<div id="nav">
	<div class="padding-10">
	
		<ul id="nav-ul">
					
			<!-- Display links to all pages with no navigation title -->
			<!-- 10, 16 and 12 refer to the key links, search results and scrolling news -->
			<!-- 70 refers to the thank you page after submitting a form -->
			<!-- 84 refers to "Hello Kathryn" -->
			<?php wp_list_pages('title_li=&exclude=10,12,16,70,84' ); ?>
			
			<!-- The link to the dashboard - only displayed to logged in users. -->
			<?php wp_register(); ?>
			
			<!-- The login link, displayed to all users. Needs <li> to display inline. -->
			<li>
				<?php wp_loginout(); ?>
			</li>
		</ul>
		
	</div>
</div>