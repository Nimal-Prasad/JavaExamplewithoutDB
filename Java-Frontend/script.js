var x = document.getElementById("login");
var y = document.getElementById("register");
var z = document.getElementById("btn");
var userDetailsList = []; 

// Register and login UI Function.
function register() {
    x.style.left = "-400px";
    y.style.left = "50px";
    z.style.left = "110px";
}
function login() {
    document.getElementById("reg-user").value = "";
    document.getElementById("reg-email").value = "";
    document.getElementById("reg-pass").value = "";
    x.style.left = "50px";
    y.style.left = "450px";
    z.style.left = "0px";
}

function RegisterAction(){
    var user = document.getElementById("reg-user").value;
    var email = document.getElementById("reg-email").value;
    var password = document.getElementById("reg-pass").value;
    var userData = {
        userId: user,
        emailId: email,
        pass: password
    };
    console.log(userData)
    var settings = {
        url: "http://localhost:8080/register",
        method: "POST",
        data: JSON.stringify(userData),
        contentType: "application/json",
        dataType: "json",
    };

    $.ajax(settings)
        .done(function (response) {
            console.log("Success:", response);
            login();
            if(response && response.result){
                alert(response.result);
            }
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            alert("Error Occured")
        });
}

function loginAction() {
    var user = document.getElementById("log-id").value;
    var password = document.getElementById("log-pass").value;
    var userData = {
        id: user,
        pass: password
    };
    var settings = {
        url: "http://localhost:8080/login",
        method: "POST",
        data: JSON.stringify(userData),
        contentType: "application/json",
        dataType: "json", 
    };
    console.log(settings)
    $.ajax(settings)
        .done(function (response) {
            console.log("Success:", response);
            if(response && response.result){
                alert(response.result);
                if(response.result !== "User Does Not Exists"){
                    loadUserDetails()
                }
            }
        })
        .fail(function (error) {
            alert("Error Occured")
        });

}

function loadUserDetails() {
    $.ajax({
        url: "http://localhost:8080/getAllUsers",
        method: "GET",
        dataType: "json",
    })
    .done(function (userDetails) {
        var userDetailsHtml = "<h2>User Details</h2>";
        userDetailsHtml += '<table class="table">';
        userDetailsHtml += '<thead><tr><th scope="col">User ID</th><th scope="col">Email</th><th scope="col">Password</th><th scope="col">Delete</th></tr></thead>';
        userDetailsHtml += '<tbody>';
        userDetailsList = userDetails
        userDetails.forEach(function (user, index) {
            userDetailsHtml += '<tr>';
            userDetailsHtml += '<td>' + user.userId + '</td>';
            userDetailsHtml += '<td>' + user.emailId + '</td>';
            userDetailsHtml += '<td>' + user.pass + '</td>';
            userDetailsHtml += '<td>';
            userDetailsHtml += '<i class="fa fa-trash fa-lg delete-icon" data-index="' + index + '" aria-hidden="true"></i>';
            userDetailsHtml += '</td>';
            userDetailsHtml += '</tr>';
        });

        userDetailsHtml += '</tbody>';
        userDetailsHtml += '</table>';

        document.getElementById("logout-section").style.display = "block";
        document.getElementById("form-section").style.display = "none";
        document.getElementById("userDetailsSection").style.display = "block";
        document.getElementById("userDetailsSection").innerHTML = userDetailsHtml;
        if(userDetailsList.length == 0){
            logout();
        }
    })
    .fail(function (error) {
        alert("Error loading user details");
    });
}

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('edit-icon')) {
        var rowIndex = event.target.getAttribute('data-index');
        var user = userDetailsList[rowIndex];
        console.log('Edit icon clicked for user:', user);
    } else if (event.target.classList.contains('delete-icon')) {
        var rowIndex = event.target.getAttribute('data-index');
        var user = userDetailsList[rowIndex];
        console.log('Delete icon clicked for user:', user);
        deleteSelectedUser(user)
    }
});

function deleteSelectedUser(user) {
    var settings = {
        url: "http://localhost:8080/deleteUser/" + user.emailId,
        method: "DELETE",
        dataType: "json",
    };

    $.ajax(settings)
        .done(function (response) {
            console.log("Success:", response);
            alert("User deleted successfully.");
            loadUserDetails();
        })
        .fail(function (error) {
            alert("Error deleting user");
        });
}

function logout() {
    document.getElementById("form-section").style.display = "block";
    document.getElementById("userDetailsSection").style.display = "none";
    document.getElementById("logout-section").style.display = "none";
}