<?php
/**
 * The base configurations of the WordPress.
 *
 * This file has the following configurations: MySQL settings, Table Prefix,
 * Secret Keys, WordPress Language, and ABSPATH. You can find more information
 * by visiting {@link http://codex.wordpress.org/Editing_wp-config.php Editing
 * wp-config.php} Codex page. You can get the MySQL settings from your web host.
 *
 * This file is used by the wp-config.php creation script during the
 * installation. You don't have to use the web site, you can just copy this file
 * to "wp-config.php" and fill in the values.
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'test');

/** MySQL database username */
define('DB_USER', '');

/** MySQL database password */
define('DB_PASSWORD', '');

/** MySQL hostname */
define('DB_HOST', 'localhost');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8');

/** The Database Collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         '%!K-.((HI$/|JkR;4e@P@QJu_(?^S7=w7V;A}{DolE-<~{9v$Dt)t3@(e+r9B24/');
define('SECURE_AUTH_KEY',  '#v5Q^M$5-MwtBA^*<~W@Mn{,~BN?,49[,7@:;9%+{(ms{}Kfhz;Ht_|yg~xzG(?*');
define('LOGGED_IN_KEY',    '|)kbfc*8@$ai1sU!D=~vjvFGw&0HuP3TVPCHUZ`yG2Q-{tIb(!b/Jek{O-(=A Gy');
define('NONCE_KEY',        'OrV;ufiE}ND<negTU_Hur~vOy$-*BHSDRT)@=4T;3`Yad=ynN8/_!6qFte2}M|v.');
define('AUTH_SALT',        'BP;wL:IxKB:jT=fr+yrjU^T5.)0#MXE*d|`!H3z $Yaz#gN-1/*[$`iV{,+fH}uT');
define('SECURE_AUTH_SALT', 'T)d:PwT p>hIqKSbH1-_iD9XsMbGoOIWKz, L kI ,+$mpgMp39MRh;LUp^a.jL9');
define('LOGGED_IN_SALT',   'lZ%>jSD/W|`|RsF/ikWvq{8klfzHl*8:-]3,aB;)k?bdus|+f[&- w[  l(q.QP(');
define('NONCE_SALT',       ')iKpSF8][-/{V}+-D{5Hty^Hs|cZF1(U7^*j)H++#-.f$z2pr3i]{>$Ym-h47g+8');

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each a unique
 * prefix. Only numbers, letters, and underscores please!
 */
$table_prefix  = 'wp_';

/**
 * WordPress Localized Language, defaults to English.
 *
 * Change this to localize WordPress. A corresponding MO file for the chosen
 * language must be installed to wp-content/languages. For example, install
 * de_DE.mo to wp-content/languages and set WPLANG to 'de_DE' to enable German
 * language support.
 */
define('WPLANG', '');

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 */
define('WP_DEBUG', true);

/* That's all, stop editing! Happy blogging. */

/** Absolute path to the WordPress directory. */
if ( !defined('ABSPATH') )
	define('ABSPATH', dirname(__FILE__) . '/');

/** Sets up WordPress vars and included files. */
require_once(ABSPATH . 'wp-settings.php');
