/**
 * 
 */

function homePage(){
//	location.reload();
//    console.log("homePage");
	updateCartLogo();
    loadCars();
}

function loadCars(){
	var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        document.getElementById("car-list").innerHTML = this.responseText;
      }
    };
    xmlhttp.open("GET","home",true);
    xmlhttp.send();
}
function updateCartLogo(){
	var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
//    	var createA = document.createElement('a');
//        var createAText = document.createTextNode(this.responseText);
//        createA.setAttribute('href', "cart");
//        createA.appendChild(createAText);
//        document.getElementById("cart-logo").appendChild(createA);
    	  document.getElementById("cart-logo").innerHTML = this.responseText;
      }
    };
    xmlhttp.open("GET","cartLogo",true);
    xmlhttp.send();
}

function addToCart(){
	var url = window.location.search;
	const urlParams = new URLSearchParams(url);
	const car_id = urlParams.get("id");
	console.log(car_id);
	var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
    	  document.getElementById("cart-logo").innerHTML = this.responseText;
        openForm();
      }
    };
    xmlhttp.open("GET","addToCart?id="+car_id,true);
    xmlhttp.send();
}

function openForm() {
    document.getElementById("order-popup").style.display = "block";
}

function closeForm() {
    document.getElementById("order-popup").style.display = "none";
}
function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
var state_tax = 0;
var cost = 0;
function calculateTotal(){
	cost = subtotal;
	var zip = document.getElementById("zip").value;
	var subtotal = parseInt(document.getElementById("price").innerText.replace("$", "").replace(",", ""));
    console.log(subtotal);
    var tax;
    if(zip == ""){
        tax = 0;
    }
    else{
//    	calculateTax(zip);
        tax = state_tax;
        //var tax = 0.0725
        console.log(tax);
    }
	var deliveryPrice = parseInt(document.getElementById("delivery").value);
	console.log(deliveryPrice);
//	subtotal = subtotal;
	tax = tax / 100;
    var taxAmount = subtotal * tax;
    var total = taxAmount + deliveryPrice + subtotal;
    console.log(taxAmount.toFixed(2));
    document.getElementById("price").innerText = "$" + numberWithCommas(subtotal.toFixed(2));
    document.getElementById("delivery-price").innerText = "+ $" + numberWithCommas(deliveryPrice.toFixed(2));
    
    tax = tax * 100;
    console.log(tax);
    document.getElementById("tax-title").innerText = "Sales Tax (" + tax.toFixed(2) + "% )";
    document.getElementById("tax").innerText = "+ $" + numberWithCommas(taxAmount.toFixed(2))
    document.getElementById("total").innerHTML = "$" + numberWithCommas(total.toFixed(2));
}

function updateDelivery(){
	document.getElementById("delivery-price").innerHTML = "$" + numberWithCommas(parseInt(document.getElementById("delivery").value).toFixed(2));
	calculateTotal();
	
}


function orderTotal(subtotal){
//	console.log(zip);
    var tax;
//	calculateTax(zip);
	tax = state_tax;
	var deliveryPrice;
	console.log(subtotal);
//	console.log(delivery);
	var delivery = document.getElementById("conf-delivery").innerText;
	if (delivery === "7-Day")
	    deliveryPrice = "3000";
	else if (delivery === "14-Day")
	    deliveryPrice = "2000";
	else if(delivery === "1-Month")
	    deliveryPrice = "1000";
	console.log(delivery);
	console.log(deliveryPrice);
	console.log(subtotal);
	tax = tax / 100;
    var taxAmount = subtotal * tax;
    console.log(taxAmount);
    deliveryPrice = parseInt(deliveryPrice);
    var total = taxAmount + deliveryPrice + subtotal;
    console.log(taxAmount.toFixed(2));
    document.getElementById("conf-price").innerHTML = "$" + numberWithCommas(subtotal.toFixed(2));
    document.getElementById("conf-delivery-price").innerHTML = "+ $" + deliveryPrice;
    
    tax = tax * 100;
    console.log(tax);
    document.getElementById("conf-tax-title").innerHTML = "Sales Tax (" + tax.toFixed(2) + "% )";
    document.getElementById("conf-tax").innerHTML = "+ $" + taxAmount.toFixed(2);
    document.getElementById("conf-total").innerText = "$" + total.toFixed(2);
}


/* Carousel source: https://www.w3schools.com/howto/howto_js_slideshow.asp */
var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}
/* Carousel */


/* Form Validation */



/* Ajax */

function calculateTax(zip){
	console.log("calculateTax");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //document.getElementById("tax").innerHTML = this.responseText;
            state_tax = this.responseText
            console.log(state_tax);
            //tax = document.getElementById("tax").innerHTML;
            //console.log(tax);
            calculateTotal(cost);
        }
    };
    xmlhttp.open("GET","tax?id="+zip,true);
    xmlhttp.send();
    
}

function resetZip(){
    document.getElementById("zip").value = "";
    document.getElementById("city").value = "";
    calculateTotal();
}


function onChangeZip(zip){
    console.log("onChangeZip");
    if (zip.length < 5) {
        resetZip();
      } else {
        var num_zip = parseInt(zip);
        getCity(num_zip);
        getState(num_zip);
        calculateTax(num_zip);
//        calculateTotal(num_zip);
      }
}

function getCity(zip){
    console.log("getCity");
    var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
        	  console.log(this.responseText);
            document.getElementById("city").value = this.responseText;
          }
        };
        xmlhttp.open("GET","city?id="+zip,true);
        xmlhttp.send();
}

function getState(zip){
    var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            var state = this.responseText;
            console.log(state);
            var options = document.getElementById("state").options;
            for (var i = 0; i < options.length; i++) {
                //console.log(options[i].value);
            if (options[i].value == state) {
                options[i].selected = true;
                break;
            }
}
          }
        };
        xmlhttp.open("GET","state?id="+zip,true);
        xmlhttp.send();
}

/* Form */
