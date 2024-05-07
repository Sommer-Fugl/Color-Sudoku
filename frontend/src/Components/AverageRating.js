import {Button, Table} from 'react-bootstrap';
import React, { useState } from 'react';

function AverageRating (props){

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>Average Rating</th>
                </tr>
            </thead>
            <tbody>
                {props.averageRating}
            </tbody>
        </Table>
    );
};

export default AverageRating;