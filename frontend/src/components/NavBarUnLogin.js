import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import React from "react";
import Button from "react-bootstrap/Button";
import { FaGithub } from "react-icons/fa";
import styles from "./style/NavBarUnLogin.module.scss";
import { useLocation, useNavigate } from "react-router-dom";

export const NavbarNoLogin = React.memo(function NavbarNoLogin() {
  const location = useLocation();
  const navigate = useNavigate();

  const handleLoginClick = () => {
    if (location.pathname !== "/login" && location.pathname !== "/signup") {
      navigate("/login");
    }
  };
  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark" style={{ height: "5.5vh" }}>
        <Container
          fluid
          className="justify-content-start justify-content-md-between"
        >
          <Navbar.Brand href="/" className={`${styles["navbar-brand-custom"]}`}>
            <img
              alt="logo"
              src={`${process.env.PUBLIC_URL}/favicon.ico`}
              width="30"
              height="30"
              className="d-inline-block align-top"
            />{" "}
            Secure Music Storage
          </Navbar.Brand>
          <Nav
            className="ms-auto align-items-center"
            style={{ marginRight: "30px" }}
          >
            <Nav.Link
              href="https://github.com/stephenarosaj/secure-music-storage"
              style={{ marginRight: "30px" }}
            >
              <FaGithub size={30} />
            </Nav.Link>
            {location.pathname !== "/login" &&
              location.pathname !== "/signup" && (
                <Button
                  variant="primary"
                  style={{ width: "100px", height: "80%" }}
                  onClick={handleLoginClick}
                >
                  Login
                </Button>
              )}
          </Nav>
        </Container>
      </Navbar>
    </>
  );
});
