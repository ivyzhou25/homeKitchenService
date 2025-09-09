export interface CartItem {
    id: number;
    name: string;
    price: number;
    quantity: number;
  }
  
  export const shoppingCart: CartItem[] = [];
  
  export function addToCart(dish: { id: number; name: string; price: number }) {
    const existing = shoppingCart.find(item => item.id === dish.id);
    if (existing) {
      existing.quantity += 1; // increment if already in cart
    } else {
      shoppingCart.push({ ...dish, quantity: 1 }); // otherwise add new
    }
  }
  
  export function getCart(): CartItem[] {
    return shoppingCart;
  }
  
  export function getTotal(): number {
    return shoppingCart.reduce((sum, item) => sum + item.price * item.quantity, 0);
  }
  
  export function clearCart() {
    shoppingCart.length = 0;
  }
  