<?php /* Template Name: Full Directory */ ?>

<?php get_header(); ?>

<?PHP
	$thisPage = $_SERVER["PHP_SELF"].'?page_id='.$_GET['page_id'];
?>

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
			<form name="searchname" action="/?page_id=7" method="get">
				<input type="hidden" name="page_id" value="<?PHP echo $_GET['page_id']; ?>" />
				<input type="text" name="search" />
				<input type="submit" value="Search" />
			</form>
		</div>	
		
		<!-- This should be dynamically generated, so that letters not used don't display -->

		<p style="text-align: center;">
<?PHP
    function starImages($rating) {
        $star = '<img src="/wp-content/plugins/directory/images/star.png" />';
        $grey = '<img src="/wp-content/plugins/directory/images/star_grey.png" />';

        return str_repeat($star, $rating).str_repeat($grey, 3 - $rating);
    }

	$sql = "SELECT * FROM dcftp_directory";
	if ($_GET['search']) {
		$sql = $sql.' WHERE name LIKE \'%'.$_GET['search'].'%\'';
	} elseif ($_GET['letter']) {
		if($_GET['letter'] == '0-9') {
			$sql = $sql.' WHERE name NOT LIKE \'A%\'';
			foreach (range('B', 'Z') as $char) {
				$sql = $sql.' AND name NOT LIKE \''.$char.'%\'';
			}
		} else {
			$sql = $sql.' WHERE name LIKE \''.$_GET['letter'].'%\'';
		}
	}

    $data = $wpdb->get_results($sql, ARRAY_A);

	if ($_GET['search']) {
		echo 'Showing retailers containing \''.strtolower($_GET['search']).'\' | <a href="'.$thisPage.'">All Entries</a>';
	} elseif ($_GET['letter']) {
    	echo 'Showing retailers beggining with \''.strtoupper($_GET['letter']).'\' | <a href="'.$thisPage.'">All Entries</a>';
    } else {
	    $cats = array();

	    foreach ($data as $entry) {
	    	$char = strtolower(substr($entry['name'], 0, 1));
	    	if (is_numeric($char)) {
	    		$char = "0-9";
	    	}

	    	$cats[$char] = true;
	    }

	    if ($cats['0-9']) {
	    	echo '<a href="'.$thisPage.'&letter=0-9">0-9</a> ';
		} else {
			echo '0-9 ';
		}
		foreach (range('A', 'Z') as $char) {
			if ($cats[strtolower($char)]) {
				echo '| <a href="'.$thisPage.'&letter='.strtolower($char).'">'.$char.'</a> ';
			} else {
				echo '| '.$char.' ';
			}
		}
	}
?>
		</p>
		<div id="entries">
<?PHP
	foreach ($data as $entry) {
?>
			<div class="entry">
				<div class="entry-left">
					<div class="entry-name">
						<div class="entry-rating">
							<?PHP echo starImages($entry['stars']); ?>
						</div>
						<?PHP echo $entry['name']; ?>
					</div>
					<p>
						<?PHP echo $entry['information']; ?>
					</p>
				</div>
				<div class="entry-right">
					<p>
						<?PHP echo $entry['addressline1']; ?><br />
						<?PHP echo $entry['addressline2']; ?><br />
						<?PHP echo $entry['city']; ?><br />
						<?PHP echo $entry['postcode']; ?>
					</p>
					<p>
						<?PHP echo $entry['phone']; ?>
					</p>
					<p>
						<a href="<?PHP echo $entry['url']; ?>">Website</a>
					</p>
				</div>
			</div>
<?PHP
	}
?>
		</div>
			
	</div>
	<div class="main-margin">
	</div>
</div>

<?php get_footer(); ?>