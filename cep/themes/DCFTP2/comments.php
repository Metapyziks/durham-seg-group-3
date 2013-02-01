<?php
	if(!empty($_SERVER['SCRIPT_FILENAME']) && 'comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('Please do not load this page directly. Thanks!');
		
	if( post_password_required() ) { ?>
		<p>
			This post is password protected. Enter the password to view comments.
		</p>
	<?php
		return;
	}
?>

<div id="comments">

	<?php if ( have_comments() ) : ?>

		<ol class="commentlist">
			<?php wp_list_comments('type=comment&callback=advanced_comment'); ?>
			<div class="comment-navigation">
				<div class="older"><?php previous_comments_link() ?></div>
				<div class="newer"><?php next_comments_link() ?></div>
			</div>
		</ol><!-- .commentlist -->
		
		
		<?php
		/* If there are no comments and comments are closed, let's leave a note.
		 * But we only want the note on posts and pages that had comments in the first place.
		 */
		if ( ! comments_open() && get_comments_number() ) : ?>
		<p class="nocomments"><?php _e( 'Comments are closed.' , 'twentytwelve' ); ?></p>
		<?php endif; ?>

	<?php endif; // have_comments() ?>
	
	<?php if (comments_open() ) : ?>
	
		<div id="leaveComment">
			<div class="respondPadding">
				<span id="respondLeaveComment">Leave a comment?</span>
				<?php comment_form(); ?>	
			</div>
		</div>
	<?php else : ?>
		<p> Comments are now closed.</p>		
	<?php endif; ?> <!-- Comments are open -->

</div><!-- #comments .comments-area -->