import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {useEffect, useState} from "react";
import {addComment, getComments} from "./_api/comment.service";
import Comments from "./Components/Comments";
import CommentsForm from "./Components/CommentsForm";
import Scores from "./Components/Scores";
import {addScore, getTopScores} from "./_api/score.service";
import ScoresForm from "./Components/ScoresForm";
import {addRating, getAverageRating} from "./_api/rating.service";
import AverageRating from "./Components/AverageRating";
import RatingForm from "./Components/RatingForm";
import LoginForm from "./Components/LoginForm";
import PasswordForm from "./Components/PasswordForm";
import {getLog} from "./_api/player_account.service";
import {Button, Col, Container, Nav, Row} from "react-bootstrap";
import React from "react";
import gameboard, {colorTile, getBoard} from "./_api/gameboard";
import GameForm from "./Components/GameForm";
import GameBoard from "./Components/GameBoard";
import GameField from "./Components/GameField";

function App() {
    const selectedGame = 'ColorSudoku';
    let bufferLogin;
    const [loggedPlayer, setLoggedPlayer] = useState(null);
    let inAccount = false;
    const [comments, setComments] = useState([]);
    const [scores, setScores] = useState([]);
    const [averageRating, setAverageRating] = useState([]);
    const [ fields, setFields ] = useState(null);
    const [diff, setDiff] = useState(null);

    const fetchData = ()=>{
        getComments(selectedGame).then(response => {
            setComments(response.data);
        })
        getTopScores(selectedGame).then(response => {
            setScores(response.data);
        })
        getAverageRating(selectedGame).then(response => {
            setAverageRating(response.data);
        })
    };

    useEffect(() => {
        const savedLogin = localStorage.getItem('loggedPlayer');
        if (savedLogin) {
            setLoggedPlayer(savedLogin);
            inAccount = true;
        }
        console.log(gameBoard);
        fetchData();
    }, []);

    const handleCommentSent = (comment) => {
        if (loggedPlayer) {
            addComment(selectedGame, loggedPlayer, comment)
                .then(() => {
                    getComments(selectedGame).then((response) => {
                        setComments(response.data);
                        toggleComponents('comments', [
                            <CommentsForm onSendComment={handleCommentSent} />,
                            <Comments comments={response.data} />,
                        ]);
                    });
                })
                .catch((error) => {
                    console.error("Error adding comment:", error);
                });
        }
    };


    const handleScoreSent = (score) => {
        if (loggedPlayer) {
            addScore(score, selectedGame, loggedPlayer)
                .then(() => {
                    getTopScores(selectedGame).then((response) => {
                        setScores(response.data);
                        toggleComponents('scores', [
                            <Scores scores={response.data} />,
                            <ScoresForm onSendScores={handleScoreSent} />,
                        ]);
                    });
                })
                .catch((error) => {
                    console.error("Error adding score:", error);
                });
        }
    };

    const handleRatingSent = (rating) => {
        if (loggedPlayer) {
            addRating(selectedGame, loggedPlayer, rating)
                .then(() => {
                    getAverageRating(selectedGame).then((response) => {
                        setAverageRating(response.data);
                        // Force re-rendering of the rating component with updated data
                        toggleComponents('averageRating', [
                            <AverageRating averageRating={response.data} />,
                            <RatingForm onSendRating={handleRatingSent} />,
                        ]);
                    });
                })
                .catch((error) => {
                    console.error("Error adding rating:", error);
                });
        }
    };

    const handleLogin = login => {
        if(login != null && !inAccount)
            bufferLogin = login;
        console.log(login);
        console.log(bufferLogin);
        fetchData()
    }

    const handlePassword = password =>{
        getLog(selectedGame, bufferLogin, password).then(isLogged => {
            if(isLogged && !loggedPlayer && !inAccount) {
                setLoggedPlayer(bufferLogin);
                localStorage.setItem('loggedPlayer', bufferLogin);
                inAccount = true;
                fetchData();
            }
        });
    }

    const [activeComponents, setActiveComponents] = useState([]);
    const [activeKey, setActiveKey] = useState(null);

    const toggleComponents = (key, components) => {
        if (key === activeKey) {
            setActiveComponents([]);
            setActiveKey(null);
        } else {
            setActiveComponents(components);
            setActiveKey(key);
        }
    };

    const [gameBoard, setGameBoard] = useState([]);

    const handleGetBoard = (difficulty) => {
        getBoard(difficulty).then(response => {
            setGameBoard(response.data);
            console.log(response.data);
        }).catch(error => {
            console.error("Error getting board:", error);
        });
    };


    return (
    <div className="App container-fluid mt-4">

        <Container fluid>
            <Row className="flex-nowrap">
                <Col as="nav" md="auto" className="sidebar">
                    <Nav defaultActiveKey="/home" className="flex-column">
                        <Nav.Link eventKey="comments" onClick={() => loggedPlayer!=null && toggleComponents('comments', [<CommentsForm onSendComment={handleCommentSent}/>,<Comments comments={comments} />])} className="nav-item">
                            <i className="bi bi-chat-dots"></i> Comments
                        </Nav.Link>
                        <Nav.Link eventKey="scores" onClick={() =>loggedPlayer!=null && toggleComponents('scores', [<ScoresForm onSendScores = {handleScoreSent}/>, <Scores scores={scores} />])} className="nav-item">
                            <i className="bi bi-graph-up"></i> Scores
                        </Nav.Link>
                        <Nav.Link eventKey="averageRating" onClick={() => loggedPlayer!=null && toggleComponents('averageRating', [<AverageRating averageRating={averageRating}/>, <RatingForm onSendRating = {handleRatingSent}/>])} className="nav-item">
                            <i className="bi bi-star-fill"></i> Rating
                        </Nav.Link>
                        <Nav.Link eventKey="login" onClick={() => toggleComponents('login', [<LoginForm onSendAccount={handleLogin}/>, <PasswordForm onSendPassword={handlePassword}/>])} className="nav-item">
                            <i className="bi bi-person-circle"></i> Account
                        </Nav.Link>
                        <Nav.Link eventKey="field" onClick={() => toggleComponents('filed', [<GameForm onSendDifficulty={handleGetBoard}/>, <GameField tiles={gameBoard.cellBoard}/>])} className="nav-item">
                            <i className="bi bi-person-circle"></i> Play
                        </Nav.Link>
                    </Nav>
                </Col>
                <Col className="content">
                    {activeComponents}
                </Col>
            </Row>
        </Container>
    </div>
  );
}

export default App;
