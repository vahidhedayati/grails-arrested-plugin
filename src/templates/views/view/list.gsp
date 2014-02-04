<% import grails.persistence.Event %>
<div data-ng-controller=${domainTitle}Ctrl>
        <h1>${domainTitle} List</h1>
</div>
<div data-ng-show="errors.showErrors" class="center red">
    <p data-ng-show="errors.showServerError">"Can not connect to the server, try later"</p>
</div>
<div>
    <div>
        <div>
            <div>
                <p>
                   Search
                </p>
            </div>
            <div>
                <form>
                    <div>
                        <label>Keywords</label>
                        <input data-ng-model="filter" type="text">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div>
        <div>
            <div>
                <p>
                    <button data-ng-click="new${domainTitle}()" type="button">Create New ${domainTitle}</button>
                </p>
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
    </div>
</div>