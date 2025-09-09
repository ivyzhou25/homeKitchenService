import { useEffect, useState } from 'react';
import { getBusinesses } from '../api';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

interface Business {
  id: number;
  bizName: string;
  bizAddr: string;
  bizCity: string;
  bizState: string;
  bizZipcode: string;
  description: string;
  userId: number;
}

const Businesses = () => {
  const [businesses, setBusinesses] = useState<Business[]>([]);

  // useEffect(() => {
  //   getBusinesses()
  //     .then(data => {
  //       console.log("Fetched businesses raw:", data);
  //       if (Array.isArray(data)) {
  //         setBusinesses(data);
  //       } else if (data && data.businesses) {
  //         setBusinesses(data.businesses); // fallback if wrapped
  //       } else {
  //         console.warn("Unexpected API response:", data);
  //       }
  //     })
  //     .catch(err => {
  //       console.error("Error fetching businesses:", err);
  //     });
  // }, []);

  useEffect(() => {
    fetch("http://localhost:8000/getAllBiz")
      .then(res => res.json())
      .then(data => setBusinesses(data));
  }, []);

  return (
    <div>
      <h1>Businesses</h1>
      <div style={{ display: "grid", gap: "1rem" }}>
        {businesses.map((biz) => (
          <div 
            key={biz.id} 
            style={{
              border: "1px solid #ccc",
              borderRadius: "8px",
              padding: "1rem",
              boxShadow: "0 2px 5px rgba(0,0,0,0.1)"
            }}
          >
            <h2>
              <Link to={`/businesses/${biz.id}`}>{biz.bizName}</Link>
            </h2>
            <p>{biz.description}</p>
            <p>
              {biz.bizAddr}, {biz.bizCity}, {biz.bizState} {biz.bizZipcode}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Businesses;