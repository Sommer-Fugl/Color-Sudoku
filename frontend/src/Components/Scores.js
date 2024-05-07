import {Button, Table} from "react-bootstrap";
import React from "react";

function Scores (props){

    return(
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>Player</th>
                <th>Score</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            {props.scores.map(score => (
                <tr key={`score-${score.ident}`}>
                    <td>{score.player}</td>
                    <td>{score.points}</td>
                    <td>{new Date (score.playedOn).toLocaleString()}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    );
};
export default Scores;