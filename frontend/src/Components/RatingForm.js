import {Button, Form} from 'react-bootstrap';
import {useForm} from "react-hook-form";
import React, {useState} from "react";

function RatingForm ({onSendRating}) {
    const [showRating, setShowRating] = useState(false);

    const toggleShowRating = () => {
        setShowRating(!showRating);
    }

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors, isValid},
    } = useForm({mode: "onChange"})

    const onSubmit = (data) => {
        if (data.rating > 0 && data.rating <= 5 ) {
            onSendRating(data.rating);
        }
        reset();
    }

    return (
        <>
            <Button onClick={toggleShowRating}>
                {showRating ? 'Hide game rating' : 'Show game rating'}
            </Button>
            {showRating && (
            <Form onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Rate</Form.Label>
                    <input className="form-control"
                           type="text"
                           {...register("rating", {
                               minLength: {value: 1, message: "Minimum rate length  is 1"},
                               maxLength: {value: 1, message: "Maximum rate length is 1"},
                               required: {value: true, message: "Required fields are required"},
                           })}
                           placeholder="Enter your rate here"/>
                    <Form.Text className="text-muted">
                        {errors.comment?.message}
                    </Form.Text>
                </Form.Group>
                <Button type = "submit">
                    Send
                </Button>
            </Form>
                )}
        </>
    )
}

export default RatingForm;