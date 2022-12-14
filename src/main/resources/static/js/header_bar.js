import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

function head_bar(info) {
    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="#home">Home</Nav.Link>
                        <Nav.Link href="#link">Link</Nav.Link>
                        <Nav.Link href="">Explore Blogs</Nav.Link>
                        {info.loginUser ? (<Nav.Link href="">My Blogs</Nav.Link>) : ""}
                        {info.loginUser ? (<Nav.Link href="">My Collections</Nav.Link>) : ""}
                    </Nav>
                </Navbar.Collapse>
                {/*<Navbar.Collapse>*/}
                {/*    <*/}
                {/*</Navbar.Collapse>*/}
            </Container>
        </Navbar>
    )
}

export default head_bar;