<?php //this function will be called in the next section
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
<?php } ?>