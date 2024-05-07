import {Table, Button} from 'react-bootstrap';
import React, { useState } from 'react';

function Comments (props){

    return (
       <>
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>Player</th>
                    <th>Comment</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
            {props.comments.map(comment => (
                <tr key={`comment-${comment.ident}`}>
                    <td>{comment.player}</td>
                    <td>{comment.comment}</td>
                    <td>{new Date(comment.commentedOn).toLocaleString()}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    </>
    );
};

export default Comments;