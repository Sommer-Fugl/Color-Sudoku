import {Button, Form, Table} from 'react-bootstrap';
import {useForm} from "react-hook-form";
import React, {useState} from "react";

function ScoresForm ({onSendScores}) {

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors, isValid},
    } = useForm({mode: "onChange"})

    const onSubmit = (data) => {
        onSendScores(data.score);
        reset();
    }

    return (
            <Form onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Score</Form.Label>
                    <input className="form-control"
                           type="text"
                           {...register("score", {
                               minLength: {value: 1, message: "Minimum score is 0"},
                               maxLength: {value: 4, message: "Maximum score is 9999"},
                               required: {value: true, message: "Required fields are required"},
                           })}
                           placeholder="Enter your score here"/>
                    <Form.Text className="text-muted">
                        {errors.comment?.message}
                    </Form.Text>
                </Form.Group>
                <Button type = "submit">
                    Send
                </Button>
            </Form>
    )
}

export default ScoresForm;