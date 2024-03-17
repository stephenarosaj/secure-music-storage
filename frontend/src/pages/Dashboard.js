import { NavbarNoLogin } from "../components/NavBarUnLogin";
import React from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Card";
import { Container, Row, Col } from "react-bootstrap";
import { Footer } from "../components/Footer";

export const Dashboard = React.memo(function Dashboard() {
  return (
    <>
      <NavbarNoLogin></NavbarNoLogin>
      <div style={{ minHeight: "100vh" }}>
        <Container
          fluid
          style={{
            backgroundColor: "#2c2c32",
            height: "60vh",
            color: "white",
          }}
        >
          <Row className="align-items-center" style={{ height: "100%" }}>
            <Col
              md={8}
              className="d-flex justify-content-center align-items-center"
            >
              <div
                style={{
                  width: "70%",
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <img
                  src={`${process.env.PUBLIC_URL}/preview.png`}
                  alt="Descriptive Alt Text"
                  style={{
                    maxWidth: "100%",
                    maxHeight: "100%",
                    objectFit: "contain",
                  }}
                />
              </div>
            </Col>
            <Col
              md={4}
              className="d-flex  justify-content-start align-items-center"
            >
              <div>
                <h1>
                  Music Project Managing
                  <br />
                  <div style={{ fontWeight: "bolder" }}>Redefined.</div>
                </h1>
                <p>
                  Secure.
                  <br />
                  Collaborative.
                  <br />
                  Access everywhere.
                </p>
              </div>
            </Col>
          </Row>
        </Container>
      </div>
      <Footer></Footer>
    </>
  );
});
