<?php
/**
 * The template for displaying all pages.
 *
 * This is the template that displays all pages by default.
 * Please note that this is the WordPress construct of pages
 * and that other 'pages' on your WordPress site will use a
 * different template.
 *
 * @package WordPress
 * @subpackage Twenty_Eleven
 * @since Twenty Eleven 1.0
 */

get_header(); ?>

<div id="main-section">
	<div class="nav-margin">
	</div>
	
	<?php get_sidebar(); ?>
	
	<div class="main-margin">
		</div>
		<div id="main">
			<!-- The content area; restricted width to leave space for sidebar. -->
			<div id="page-title">
				<!-- The title of the page. Spans full width (excluding navigation) -->
				About the Partnership
			</div>
			<div id="aside">
				<div class="padding-10">
					<!-- Home page only. Contains key links and recent news. An 'archive' of blog posts? -->
					<div id="social-buttons">
						<img src="<?php bloginfo('template_directory'); ?>/layoutImages/twitter-button.png">
						<img src="<?php bloginfo('template_directory'); ?>/layoutImages/facebook-button.png">
					</div>
					<marquee scrollamount="2" direction="up" loop="true" onmousedown="this.stop();" onmouseup="this.start();">
						<div class="padding-4">
							<h2>21st January</h2>
							<p>
								Today I ate a biscuit. It was a very nice biscuit. It had chocolate chips. <a href="www.google.com">Biscuits!</a>
							</p>
							<h2>8th January</h2>
							<p>
								Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
							</p>
						</div>
					</marquee>
					
					<h1>Key Links</h1>
					<p>
						Some handy-dandy links to fun places and stuff.
					</p>
					<div id="key-links">
						<a href="#">First link</a> <br />
						<p>
							Description of the link goes here.
						</p>
						<a href="#">Second link</a> <br />
						<p>
							This link really recommended!
						</p>
						<a href="#">Red hat link</a> <br />
						<p>
							This link wears a red hat.
						</p>
						<a href="#">Easily distracted link</a> <br />
						<p>
							The sky is pretty today.
						</p>
						<a href="#">Factual link</a> <br />
						<p>
							And this is the last one.
						</p>
					</div>
				</div>
			</div>
			<div id="main-with-aside">
				<p>
					DCFTP is a Durham-based group which is active in the promotion of Fairtrade and fair trade primarily within Durham and to maintain Fairtrade City status which it earned in May 2007, renewed successfully and is currently working to renew again soon. However, the arrival of a unitary authority in April 2009 meant that the group now works alongside Chester-le-Street and there is a separate County Durham Fairtrade Steering Group as well.
				</p>
				<p>
					DCFTP draws its membership from local bodies, schools, community groups, faith groups, University students, businesses and citizens who all share a desire to support and represent the social and economic sense of Fairtrade, fair trade and local trade in the area. The group is always keen to welcome new members and fresh ideas, and anyone wishing to enquire about involvement should please email the Chair, Kathryn Sygrove on: kathrynssygrove@oink.co.uk
				</p>
				<p>
					The next few pages will give you an overview about what exactly Fairtrade is, how it differs from fair trade or fairly-traded products, and what Goals are required to achieve and maintain Fairtrade City status. It then expands to explain the spread of Fairtrade over parts of County Durham. Beyond that, there is information on forthcoming events, meetings past, present and imminent, and our latest news. Wherever possible, we strive to work with other groups based in Durham and beyond who share similar principles to us, eg Durham Local Food Network, Durham Churches Together, Durham University Greenspace, and more.
				</p>
				<p>
					As this is a very recent transition from a previous website, please bear with us as we transfer information across over a period of time.
				</p>
			</div>
		</div>
		<div class="main-margin">
		</div>
	</div>
	
	<?php get_footer(); ?>