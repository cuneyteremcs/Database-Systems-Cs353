<?php
	DEFINE ('user', 'cuneyt.erem');
	DEFINE ('pass', 'h3b7t7fg');
	DEFINE ('host', 'dijkstra2.ug.bcc.bilkent.edu.tr');
	DEFINE ('dname', 'cuneyt_erem');

	$dbc = @mysqli_connect(host, user, pass, dname)
	OR die('Could not connect to MySQL: '.mysqli_connect_error());

	$q1 = "SELECT channel.cname, show2.pname, host.title as h1 , host.name, show2.day, show2.time,guest.title as h2,guest.gname,guest.profession,guest.short_bio, guest_show.date  FROM host,channel
		,show2,guest,guest_show WHERE show2.hid = host.hid AND show2.cid = channel.cid AND guest_show.gid = guest.gid AND guest_show.sid = show2.sid ";

	$res = @mysqli_query($dbc, $q1);
	
	if($res){

	echo '<table align="left"
	cellspacing="6" cellpadding="9">

	<tr><td align="left"><b>cname</b></td>
	<td align="left"><b>pname</b></td>
	<td align="left"><b>title</b></td>
	<td align="left"><b>name</b></td>
	<td align="left"><b>day</b></td>
	<td align="left"><b>time</b></td>
	<td align="left"><b>title</b></td>
	<td align="left"><b>gname</b></td>
	<td align="left"><b>profession</b></td>
	<td align="left"><b>short_bio</b></td>
	</tr>';

	while($row = mysqli_fetch_array($res)){

	echo '<tr><td align="left">' . 
	$row['cname'] . '</td><td align="left">' . 
	$row['pname'] . '</td><td align="left">' .
	$row['h1'] . '</td><td align="left">' . 
	$row['name'] . '</td><td align="left">' .
	$row['day'] . '</td><td align="left">' . 
	$row['time'] . '</td><td align="left">'.
	$row['h2'] . '</td><td align="left">'.
	$row['gname'] . '</td><td align="left">'.
	$row['profession'] . '</td><td align="left">'.
	$row['short_bio'] . '</td><td align="left">';

	echo '</tr>';
	}

	echo '</table>';

	} else {

	echo "query error<br />";

	echo mysqli_error($dbc);

	}

	mysqli_close($dbc);

	?>