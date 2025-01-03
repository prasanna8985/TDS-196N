import React, { useState } from 'react';
import './App.css';

function App() {
  const [withdrawalAmount, setWithdrawalAmount] = useState('');
  const [result, setResult] = useState('');

  const calculateTDS = () => {
    if (!withdrawalAmount || withdrawalAmount <= 0) {
      setResult('Please enter a valid withdrawal amount.');
      return;
    }

    let tds = 0;
    let message = '';

    const amount = parseFloat(withdrawalAmount);

    if (amount > 10000000) {
      tds = (amount - 10000000) * 0.05 + 160000;
      message = `TDS at 5% on amount exceeding ₹1 crore. Total TDS: ₹${tds.toFixed(2)}`;
    } else if (amount > 2000000) {
      tds = (amount - 2000000) * 0.02;
      message = `TDS at 2% on amount exceeding ₹20 lakh. Total TDS: ₹${tds.toFixed(2)}`;
    } else {
      message = 'No TDS applicable.';
    }

    setResult(message);
  };

  return (
    <div className="App">
      <header>
        <h1>TDS Calculator (Section 194N)</h1>
        <p>Calculate applicable TDS on cash withdrawals</p>
      </header>
      <main>
        <div className="form-group">
          <label htmlFor="withdrawal">Enter Withdrawal Amount (₹):</label>
          <input
            type="number"
            id="withdrawal"
            value={withdrawalAmount}
            onChange={(e) => setWithdrawalAmount(e.target.value)}
            placeholder="Enter the amount"
          />
        </div>
        <button className="btn" onClick={calculateTDS}>
          Calculate TDS
        </button>
        <div className="result">{result}</div>
      </main>
    </div>
  );
}

export default App;
