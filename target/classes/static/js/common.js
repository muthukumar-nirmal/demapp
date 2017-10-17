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

function removeActiveClass(){
    $('#aboutUs').removeClass('active');
    $('#contactUs').removeClass('active');
    $('#home').removeClass('active');
    $('#loginId').removeClass('active');
    $('#reigisterId').removeClass('active');
}

function hideAndShowMenuBeforeLogin()
{
    $('#home').hide();
    $('#contactUs').hide();
    $('#aboutUs').hide();
}

function hideAndShowBodySection()
{
    $('#aboutUsInformation').hide();
    $('#contactUsInformation').hide();
    $('#userHomeInformation').hide();
    $('#signInInformation').hide();
    $('#registerInformation').hide();
}

function loadHome()
{
    removeActiveClass();
    hideAndShowBodySection();
    $('#home').addClass('active');
    $('#userHomeInformation').show();
}

function loadAboutUs()
{
    removeActiveClass();
    hideAndShowBodySection();
    $('#aboutUs').addClass('active');
    $('#aboutUsInformation').show();
}

function loadContactUs()
{
    removeActiveClass();
    hideAndShowBodySection();
    $('#contactUs').addClass('active');
    $('#contactUsInformation').show();
}


