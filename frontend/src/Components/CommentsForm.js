import {Button, Form} from 'react-bootstrap';
import {useForm} from "react-hook-form";
import React, {useState} from "react";


function CommentsForm ({onSendComment}){

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
    } = useForm({mode: "onChange"})

    const onSubmit = data => {
        onSendComment(data.comment);
        reset();
    }

    return (
            <Form onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Comment</Form.Label>
                    <input className= "form-control"
                           type="text"
                            {...register("comment", {
                                minLength: {value: 3, message: "Minimum comment length is 3"},
                                maxLength: {value:150, message: "Maximum comment length is 150"},
                                required: {value: true, message: "Required fields are required"},
                            })}
                           placeholder="Enter your comment here" />
                    <Form.Text className="text-muted">
                        {errors.comment?.message}
                    </Form.Text>
                </Form.Group>
                <Button type = "submit">
                    Send
                </Button>
            </Form>
    );
};
export default CommentsForm;