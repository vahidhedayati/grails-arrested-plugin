<div>
        <h1>'@class.name@'</h1>
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
                        <input type="text">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div>
        <div>
            <div>
                <p>
                    <button data-ng-click="new@class.instance@()" type="button">Create New @class.name@</button>
                </p>
                <br/>
            </div>
            <div class="panel-body">
                <div>
                    <div>
                        <ul>
                            <li  data-ng-repeat="">
                                <div>



                                </div>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>