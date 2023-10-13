'use client'
import './globals.css'
import styles from '../mainPage.module.css';
import { Container,Row,Col } from 'react-bootstrap';
export default function Home() {


  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
       <Container className={styles.container_main}>
        <Row>
          <Col>
            <h1>Main</h1>
          </Col>
        </Row>
      </Container>
    </main>
  )
}
