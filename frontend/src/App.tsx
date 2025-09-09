import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import Businesses from "./pages/Businesses";
import BusinessDetail from "./pages/BusinessDetail";
import ShoppingCart from './pages/ShoppingCart';

export default function App() {
  return (
    <Router>
      <nav style={{ padding: '10px', background: '#eee' }}>
        <Link to="/" style={{ marginRight: '10px' }}>Home</Link>
        <Link to="/cart">Shopping Cart</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Businesses />} />
        <Route path="/businesses/:id" element={<BusinessDetail />} />
        <Route path="/cart" element={<ShoppingCart />} />
      </Routes>
    </Router>
  );
}

// export default function App() {
//   const [businesses, setBusinesses] = useState<Business[]>([]);
//   const [loading, setLoading] = useState(true);

//   useEffect(() => {
//     fetch("http://localhost:8000/getAllBiz") // backend endpoint
//       .then((res) => res.json())
//       .then((data) => {
//         console.log("Fetched businesses:", data);
//         setBusinesses(data);
//         setLoading(false);
//       })
//       .catch((err) => {
//         console.error("Error fetching businesses:", err);
//         setLoading(false);
//       });
//   }, []);

//   if (loading) return <p>Loading...</p>;

//   return (
//     <div style={{ padding: "20px" }}>
//       <h1>Businesses</h1>
//       {businesses.length > 0 ? (
//         <ul>
//           {businesses.map((biz) => (
//             <li key={biz.id}>
//               <strong>{biz.bizName}</strong> — {biz.description}
//               <br />
//               {biz.bizAddr}, {biz.bizCity}, {biz.bizState} {biz.bizZipcode}
//             </li>
//           ))}
//         </ul>
//       ) : (
//         <p>No businesses found.</p>
//       )}
//     </div>
//   );
// }