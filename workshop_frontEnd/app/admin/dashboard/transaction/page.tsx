
  'use client'
  import styles from "@/app/admin/ui/dashboard/products/products.module.css";
  import Search from "@/app/admin/ui/dashboard/search/search";
  import Pagination from "@/app/admin/ui/dashboard/pagination/pagination";
  import React, { useEffect, useState } from 'react';
  import ApiService from '@/app/services/ApiService';
  import { useSession } from 'next-auth/react';


  interface TransactionData {
    id: number;
    type: string;
    status: boolean;
    transactionDate: string;
    amount: number;
    user_Name: string;
  }
  const TransactionPage = () => {
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const [transactions, setTransactions] = useState<TransactionData[]>([]);
    const [currentPage, setCurrentPage] = useState<number>(1);
    const [transactionsPerPage] = useState<number>(5);

    useEffect(() => {
      if (session) {
        const fetchData = async () => {
          try {
            const listTransactionResponse = await apiService.listTransactionAdmin(); // Adjust to the actual method in your ApiService
            if (listTransactionResponse.data) {
              console.log('data', listTransactionResponse);
              setTransactions(listTransactionResponse.data);
            }
          } catch (error) {
            console.error("Error fetching courses:", error);
          }
        };
        fetchData();
      }
    }, [session]);

    // Pagination logic
    const indexOfLastTransaction = currentPage * transactionsPerPage;
    const indexOfFirstTransaction = indexOfLastTransaction - transactionsPerPage;
    const currentTransaction = transactions.slice(indexOfFirstTransaction, indexOfLastTransaction);

    const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

    
  console.log('id',transactions);

    return (
      <div className={styles.container}>
        <div className={styles.top}>
          <Search placeholder="Search for a request..." />
        </div>
        {currentTransaction.length > 0 && (
          <>
            <table className={styles.table}>
              <thead className="text-center">
                <tr>
                  <th>Request Name</th>
                  <th>Create Date</th>
                  <th>User Name</th>
                  <th>Amount</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody className="text-center">
                {currentTransaction.map((transaction) => (
                  <tr key={transaction.id}> 
                    <td>{transaction.type}</td>
                    <td>{transaction.transactionDate}</td>
                    <td>{transaction.user_Name}</td>
                    <td>{transaction.amount}</td>
                    <td>{transaction.status}</td>
                  
                    <td>
                      <div className={styles.buttons}>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            <Pagination
              itemsPerPage={transactionsPerPage}
              totalItems={transactions.length}
              paginate={paginate}
              currentPage={currentPage}
            />
          </>

        )}
      </div>
    );
  };

  export default TransactionPage;
