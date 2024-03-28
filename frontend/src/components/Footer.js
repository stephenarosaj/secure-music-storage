import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { MdFeedback } from "react-icons/md";
import { FaGithub } from "react-icons/fa";

export const Footer = React.memo(function Footer() {
  return (
    <footer
      style={{
        backgroundColor: "#333",
        color: "white",
        height: "10vh",
      }}
    >
      <Container>
        <Row
          className="justify-content-center align-items-center"
          style={{ height: "2vh" }}
        ></Row>
        <Row
          className="justify-content-center align-items-center"
          style={{ height: "2vh" }}
        >
          <Col md="auto">
            <a
              target="_blank"
              rel="noopener noreferrer"
              href="https://github.com/stephenarosaj/secure-music-storage"
              style={{ color: "inherit", textDecoration: "none" }}
            >
              <FaGithub size={30} style={{ marginRight: "30px" }} />
            </a>
            <a
              target="_blank"
              rel="noopener noreferrer"
              href="https://github.com/stephenarosaj/secure-music-storage/issues"
              style={{ color: "inherit", textDecoration: "none" }}
            >
              <MdFeedback size={30} />
            </a>
          </Col>
        </Row>

        <Row
          className="justify-content-center align-items-center"
          style={{ height: "6vh" }}
        >
          <Col md="auto" style={{ display: "flex", alignItems: "center" }}>
            <img
              src={`${process.env.PUBLIC_URL}/Brown-logo.png`}
              alt="logo"
              style={{ height: 40, marginRight: "5px" }}
            />
            <p style={{ margin: "0", fontStyle: "italic", fontWeight: "bold" }}>
              CSCI 2340 Software Engineering @ 2024. Built with React.
            </p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
});
