export function createFormAddUser(){
    let content = `
    <span class="close-button align-self-end">&times;</span>
    <h2>Add User</h2>
    <div id="add-user-form">
        <div class="row m-1">
            <div class="col"><label for="username">Username:</label></div>
            <div class="col"><input id="username" name="username" required autofocus></div>
        </div>
        <div class="row m-1">
            <div class="col"><label for="email">Email:</label></div>
            <div class="col"><input type="email" id="email" name="email" required></div>
        </div>
        <div class="row m-1">
            <div class="col"><label for="password">Password:</label></div>
            <div class="col"><input type="password" id="password" name="password" required></div>
        </div>
        <div class="row m-1">
            <div class="col"><button type="submit">Submit</button></div>
        </div>
    </div>`;

    return $("<form>").addClass("modal-content p-4 rounded shadow").html(content);
}

export function createEditUserSection(id, username, mail, role){
    return `
    <tr class="edit-mode">
        <td><span><button type="button" id="submit-btn"><i class="fa-solid fa-check"></i></button></span><span><button type="button" id="cancel-btn"><i class="fa-solid fa-xmark"></i></button></span></td>
        <td><input value="${id}" disabled="disabled"></td>
        <td><input value="${username}" required></td>
        <td><input value="${mail}" required></td>
        <td><input value="${role}" disabled="disabled"></td>
    </tr>`;
}

export function createUserManagement(dataList){
    let content = '';
    let counter = 0;

    dataList.forEach(data => {
        content += `
        <tr class="user-info">
            <td><input type="checkbox" class="manage-checkbox" data-id="${counter}"></td>
            <td><span>${data.id}</span></td>
            <td><span>${data.username}</span></td>
            <td><span>${data.mail}</span></td>
            <td><span>${data.role}</span></td>
        </tr>`

        counter++;
    });

    return `
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="col-3">Side Bar</div>
                </div>
                <div class="col-8">
                    <div class="row toolbar d-flex">
                        <div class="col d-flex">
                            <div class="header-filter-search d-flex justify-content-center">
                                <div class="filter-search">
                                    <div class="search-icon-filter-search">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </div>

                                    <div class="caret-icon-filter-search">
                                        <i class="fa-solid fa-caret-down"></i>
                                    </div>

                                    <input placeholder="Search by Username" type="search" name="search_by_username" class="input-filter-search">

                                    <div id="dropdownSearch" class="shadow bg-body rounded p-3">
                                        <div class="form-search">
                                            <input placeholder="Search by Username" type="search" name="search_by_username">
                                            <input placeholder="Search by ID" type="search" name="search_by_id">
                                            <input placeholder="Search by Mail" type="search" name="search_by_mail">  
                                        </div>
                                        <div class="form-footer d-flex justify-content-end">
                                            <button type="button" name="form-search-button"'>Search</button>
                                        </div>                              
                                    </div>  
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="header-filter-button d-flex justify-content-end">
                                <button type="button" id="add-user" onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-plus"></i></button> 
                                <button type="button" id="del-user" onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-minus"></i></button> 
                                <button type="button" id="edit-user" onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-gear"></i></button> 
                                <button type="button" id="none" onclick="handleToolBarBtn(this)"></button>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <table id="user-table">
                            <thead>
                            <tr>
                                <th></th>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Mail</th>
                                <th>Role</th>
                            </tr>
                            </thead>
                            <tbody> ${content}</tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>`;
}