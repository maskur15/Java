/**
 * Created by ASUS on 20-Sep-23.
 */

//this is for serach function a tbale in the html page
function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.querySelector("table");
    tr = table.getElementsByTagName("tr");

    for (i = 1; i < tr.length; i++) { // Start from 1 to skip the table header row
        //coulumn index is 0 based
        td = tr[i].getElementsByTagName("td")[1]; // Adjust the index for the column you want to search
        td2= tr[i].getElementsByTagName("td")[0]; //id
        if(td && td2){
            txt1 = td.textContent || td.innerText;
            tx2 = td2.textContent || td2.innerText;
            if(txt1.toUpperCase().indexOf(filter)>-1 || tx2.toUpperCase().indexOf(filter)>-1){
                tr[i].style.display="";
            }
            else{
                tr[i].style.display="none";
            }
        }
        else if (td ) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
        else if(td2){
            txtValue = td2.textContent || td2.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

//this is for checking the password and confirm password in registration page
document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("registrationForm");
    form.addEventListener("submit", function(event) {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password !== confirmPassword) {
            alert("Passwords do not match. Please check and try again.");
            event.preventDefault(); // Prevent form submission
        }
    });
});

//checking password length whether its 8 character long or not
var passwordInput = document.getElementById("password");
var passwordError=document.getElementById("passwordError");
var submitButton = document.querySelector("button[type='submit']");

passwordInput.addEventListener("input",function () {
    var password = passwordInput.value
    var validPassword = true;
    if (password.length<8){
        validPassword=false;
        passwordError.innerHTML="Password must be at least 8 character";
        submitButton.disabled= true;
    }
    else{
        passwordError.innerHTML="";
        submitButton.disabled=false;

    }

});

// this is for checking the format of email wheather it is valid or not
var  emailInput = document.getElementById("email");
var emailError = document.getElementById("emailError");
var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
document.getElementById("registrationForm").addEventListener("submit",
    function (event) {
        var email = emailInput.value;
        if(!emailPattern.test(email)){
            //email format is invalid
            emailError.innerHTML= "Invalid email format";
            event.preventDefault();
        }

    });