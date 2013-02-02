<?php // Apply custom styling to comments.
function advanced_comment($comment, $args, $depth) {
   $GLOBALS['comment'] = $comment; ?>
 
<li id="li-comment-<?php comment_ID() ?>">
	<div <?php comment_class(); ?>>
	<div class="comment-wrapper">
		<div class="respondPadding">
			<div class="comment-author vcard">
				<?php echo get_avatar($comment,$size='48'); ?>
				<div class="comment-meta"<a href="<?php the_author_meta( 'user_url'); ?>">
					<cite class="comment-author-name">
						<?php printf(__('%s'), get_comment_author_link()) ?></a>
					</cite>
				</div>
				<small>
					<?php printf(__('%1$s at %2$s'), get_comment_date(),  get_comment_time()) ?>
					<?php edit_comment_link(__('Edit'),'  ','') ?>
				</small>
			</div>
			 
			<div class="clear"></div>
		 
			<?php if ($comment->comment_approved == '0') : ?>
				<div class="awaiting_moderation">
					<i><?php _e('Your comment is awaiting moderation.') ?></i>
				</div>
			<?php endif; ?>
		 
			<div class="comment-text">	
				<?php comment_text() ?>
			</div>
		 
			<div class="reply">
				<?php comment_reply_link(array_merge( $args, array('depth' => $depth, 'max_depth' => $args['max_depth']))) ?>
			</div>
		   
			<div class="clear"></div>
		</div>
	</div>
	</div>
<?php } 

	// Add a welcome widget to the dashboard.
	
	add_action('wp_dashboard_setup', 'my_custom_dashboard_widgets');
	
	function my_custom_dashboard_widgets() {
		global $wp_meta_boxes;
		wp_add_dashboard_widget('custom_help_widget', 'Theme Support', 'custom_dashboard_help');
	}

	function custom_dashboard_help() {
		echo '<p>Hello! Welcome to the Durham City Fair Trade Partnership dashboard.</p><p>From here you can manage your website, including adding (or removing) pages, moderating comments and changing some settings. You can find a brief guide for some features at the page Hello Kathryn! (which is only visible to you through the dashboard).</p><p>We hope you enjoy using the website!</p><p>-- Emma, James, Alice, James and Charles</p>';
	}
	
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
			echo '<div id="dist'.$entry['outletID'].'" class="entry-hidden"></div>';
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
			echo '<a href="'.$entry['url'].'">Website</a> <a href="/directory/map/?retailer_id='.$entry['outletID'].'" id="maplink'.$entry['outletID'].'">Find on Map</a>';
			echo '</p>';
			echo '</div>';
			echo '</div>';
		}
		echo '</div>';
	}

?>
