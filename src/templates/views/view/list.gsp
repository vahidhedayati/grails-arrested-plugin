<% import grails.persistence.Event %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${domainTitle}</title>
    <g:javascript src="${domainTitle}Ctrl.js" />
</head>
<body>
<div data-ng-controller="${domainTitle}Ctrl">
    <h1 class="h1Title">${domainTitle} List</h1>
</div>
<div data-ng-show="errors.showErrors" class="center red">
    <p data-ng-show="errors.showServerError">"Can not connect to the server, try later"</p>
</div>
<div>
    <div>
        <ul id="Menu" class="nav nav-pills margin-top-small">
            <li class="${ params.action == "create" ? 'active' : '' }">
                <g:link action="create">New ${domainTitle}</g:link>
            </li>
        </ul>
        <br/>
    </div>
    <div>
        <div>
            <div>
                <%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
                allowedNames = domainClass.name << 'dateCreated' << 'lastUpdated'
                props = domainClass.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                %>
                <ul>
                    <li data-ng-repeat="instance in ${domainTitle}s">
                        <a data-ng-click="edit(instance)">
                            <div>
                                <%  props.eachWithIndex { p, i ->
                                    if (i < 6) {
                                %>
                                <strong>{{instance.${p.name}}}</strong>
                                <% } }%>
                            </div>
                        </a>
                    </li>
                </ul>

            </div>
        </div>
    </div>
</div>
</body>
</html>
