#!/usr/bin/env python3
import logging

# Configure logging
logging.basicConfig(level=logging.DEBUG, format='%(levelname)s: %(message)s')

def parse_transactions(raw_data):
    transactions = []
    for record in raw_data:
        parts = record.split(",")
        try:
            if len(parts) < 3:
                logging.warning(f"Skipping incomplete record: {record}")
                continue

            amount = float(parts[1].strip())
            description = parts[3].strip() if len(parts) > 3 else "N/A"

            transaction = {
                "date": parts[0].strip(),
                "amount": amount,
                "type": parts[2].strip().lower(),
                "description": description
            }

            transactions.append(transaction)
            logging.debug(f"Parsed transaction: {transaction}")
        except (ValueError, IndexError) as e:
            logging.error(f"Failed to parse record: {record} | Error: {e}")
    return transactions


def calculate_balance(transactions):
    balance = 0
    for txn in transactions:
        if txn["type"] == "credit":
            balance += txn["amount"]
        elif txn["type"] == "debit":
            balance -= txn["amount"]
        else:
            logging.warning(f"Unknown transaction type: {txn['type']}")
    logging.info(f"Calculated balance: {balance}")
    return round(balance, 2)


def generate_summary(transactions):
    credit_txns = [t for t in transactions if t["type"] == "credit"]
    debit_txns = [t for t in transactions if t["type"] == "debit"]

    credit_count = len(credit_txns)
    debit_count = len(debit_txns)
    total_credit = sum(t["amount"] for t in credit_txns)

    average_credit = total_credit / credit_count if credit_count > 0 else 0

    try:
        largest_txn = max(transactions, key=lambda t: t["amount"])
    except ValueError:
        largest_txn = None
        logging.warning("No transactions found for largest transaction.")

    summary = {
        "credits": credit_count,
        "debits": debit_count,
        "average_credit": round(average_credit, 2),
        "largest_txn": largest_txn
    }

    logging.debug(f"Generated summary: {summary}")
    return summary


def main():
    raw_data = [
        "2025-07-01, 1200, CREDIT, Salary",
        "2025-07-02, 300, debit, Grocery",
        "2025-07-03, , debit, Restaurant",        # Invalid amount
        "2025-07-04, 200, DEBIT",                 # Missing description
        "2025-07-05, 400, credit, Freelance, Bonus"
    ]

    transactions = parse_transactions(raw_data)
    balance = calculate_balance(transactions)
    summary = generate_summary(transactions)

    print("\nFinal balance:", balance)
    print("Summary:")
    print("Credits:", summary["credits"])
    print("Debits:", summary["debits"])
    print("Avg Credit:", summary["average_credit"])
    if summary["largest_txn"]:
        print("Largest Txn:", summary["largest_txn"]["description"], "-", summary["largest_txn"]["amount"])
    else:
        print("No valid largest transaction found.")


if __name__ == "__main__":
    main()

