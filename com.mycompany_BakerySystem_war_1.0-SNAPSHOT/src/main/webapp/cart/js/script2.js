/*
This code is for a shopping cart that allows users to add items to a cart and view the cart. When the user clicks on the cart icon (specified by the '#cart-icon' selector), the class 'cart-active' is added to the cart element (specified by the '.cart' selector) which will display the cart to the user. The user can then close the cart by clicking on the '#cart-close' element, which removes the 'cart-active' class from the cart element.
When the page loads, the 'DOMContentLoaded' event is listened for and the function 'loadFood' is called. This function calls another function 'loadContent' which adds event listeners for removing items from the cart, changing the quantity of items in the cart, and adding items to the cart.
When an item is added to the cart, the code retrieves the title, price, and image source of the item from the HTML, creates an object of the item, checks if the item already exists in the cart, and if it doesn't, it adds the item to the cart and updates the total cost.
The 'updateTotal' function calculates the total cost of all the items in the cart by iterating through each cart item, getting the price and quantity of the item, and updating the total accordingly. 
*/

const btnCart=document.querySelector('#cart-icon');
const cart=document.querySelector('.cart');
const btnClose=document.querySelector('#cart-close');
var placeOrderBtn = document.getElementById("placeOrderBtn");

btnCart.addEventListener('click',()=>{
  cart.classList.add('cart-active');
});

btnClose.addEventListener('click',()=>{
  cart.classList.remove('cart-active');
});

document.addEventListener('DOMContentLoaded',loadFood);

function loadFood(){
  loadContent();

}

function loadContent(){
  //Remove Food Items  From Cart
  let btnRemove=document.querySelectorAll('.cart-remove');
  btnRemove.forEach((btn)=>{
    btn.addEventListener('click',removeItem);
  });

  //Product Item Change Event
  let qtyElements=document.querySelectorAll('.cart-quantity');
  qtyElements.forEach((input)=>{
    input.addEventListener('change',changeQty);
  });

  //Product Cart
  
  let cartBtns=document.querySelectorAll('.add-cart');
  cartBtns.forEach((btn)=>{
    btn.addEventListener('click',addCart);
  });

  updateTotal();
}


//Remove Item
function removeItem(){
  if(confirm('Are Your Sure to Remove')){
    let title=this.parentElement.querySelector('.cart-food-title').innerHTML;
    itemList=itemList.filter(el=>el.title!==title);
    this.parentElement.remove();
    loadContent();
  }
}

//Change Quantity
function changeQty(){
  if(isNaN(this.value) || this.value<1){
    this.value=1;
  }
  loadContent();
}

let itemList=[];

//Add Cart
function addCart(){
 let food=this.parentElement;
 let title=food.querySelector('.food-title').innerHTML;
 let price=food.querySelector('.food-price').innerHTML;
 let imgSrc=food.querySelector('.food-img').src;
 //console.log(title,price,imgSrc);
 
 let newProduct={title,price,imgSrc};

 //Check Product already Exist in Cart
 if(itemList.find((el)=>el.title===newProduct.title)){
  alert("Product Already added in Cart");
  return;
 }else{
  itemList.push(newProduct);
 }


let newProductElement= createCartProduct(title,price,imgSrc);
let element=document.createElement('div');
element.innerHTML=newProductElement;
let cartBasket=document.querySelector('.cart-content');
cartBasket.append(element);
loadContent();
}


function createCartProduct(title,price,imgSrc){

  return `
  <div class="cart-box">
  <img src="${imgSrc}" class="cart-img">
  <div class="detail-box">
    <div class="cart-food-title">${title}</div>
    <div class="price-box">
      <div class="cart-price">${price}</div>
       <div class="cart-amt">${price}</div>
   </div>
    <input type="number" value="1" class="cart-quantity">
  </div>
  <ion-icon name="trash" class="cart-remove"></ion-icon>
</div>
  `;
}

function updateTotal()
{
  const cartItems=document.querySelectorAll('.cart-box');
  const totalValue=document.querySelector('.total-price');

  let total=0;

  cartItems.forEach(product=>{
    let priceElement = product.querySelector('.cart-price');
    let price=parseFloat(priceElement.innerHTML.replace("R",""));
    let qty=product.querySelector('.cart-quantity').value;
    total+=(price*qty);
    product.querySelector('.cart-amt').innerText="R"+(price*qty);

  });

  totalValue.innerHTML='R'+total;


  // Add Product Count in Cart Icon

  const cartCount=document.querySelector('.cart-count');
  let count=itemList.length;
  cartCount.innerHTML=count;

  if(count===0){
    cartCount.style.display='none';
  }else{
    cartCount.style.display='block';
  }


}

  placeOrderBtn.addEventListener("click", function() {
    // Redirect the user to the desired page
    window.location.href = "http://localhost:8080/BakerySystem/PaymentServlet"; 
  });