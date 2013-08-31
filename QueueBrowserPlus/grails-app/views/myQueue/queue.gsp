<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <meta name="layout" content="main"/>
    <title>Netflix Queue</title>
</head>

<body style="max-width:100%">
<div class="body">
    <span class="pagination" style="display: block; margin-left:auto; margin-right: auto">
        <g:paginate action="queue" total="${queueCount}" max="${max}" offset="${offset}"/>
    </span>
    <table>
        <thead>
        <g:sortableColumn property="position" title="Position"/>
        <g:sortableColumn property="title" title="Title"/>
        <g:sortableColumn property="averageRating" title="Average Rating"/>
        <g:sortableColumn property="releaseYear" title="Release Year"/>
        <g:sortableColumn property="rtRatings.criticsScore" title="Critics Score"/>
        <g:sortableColumn property="rtRatings.audienceScore" title="Audience Score"/>
        </thead>
        <g:render template="queueItem" collection="${items}"></g:render>
    </table>
    <span class="pagination" style="display: block; margin-left:auto; margin-right: auto">
        <g:paginate action="queue" total="${queueCount}" max="${max}" offset="${offset}"/>
    </span>
</div>
</body>
</html>