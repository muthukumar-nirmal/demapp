/**
 * 
 */

var BASE_URL = 'http://localhost:8080/';

function isValidEmailAddress(emailAddress) 
{
    var pattern = new RegExp(/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/);
    return pattern.test(emailAddress);
};

// Show and Remove active class before login to this application

function removeActiveClass()
{
	// For all the user 
	$('#home').removeClass('active');
    $('#aboutUs').removeClass('active');
    $('#contactUs').removeClass('active');
    $('#reigisterId').removeClass('active');
	$('#loginId').removeClass('active');
    
    // For normal user
    $('#userDashboard').removeClass('active');
    $('#expense').removeClass('active');
    $('#testApp').removeClass('active');
    
    // For admin user
    $('#dashboard').removeClass('active');
    $('#listUser').removeClass('active');
    $('#listCategory').removeClass('active');
}



