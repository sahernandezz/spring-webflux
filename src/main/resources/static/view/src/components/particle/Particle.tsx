import useWindowSize from "@rooks/use-window-size";
import * as React from "react";

export interface OptionsParticle {
    url: string;
    background?: string | undefined;
    color?: string | undefined;
    width?: number | undefined;
    height?: number | undefined;
    maxParticles?: number | undefined;
    scale?: number | undefined;
    entropy?: number | undefined;
    className?: string | undefined;
}

import ParticleImage, {
    ParticleOptions,
    Vector,
    forces,
    ParticleForce
} from "react-particle-image";

const Particle = (props: OptionsParticle) => {
    const particleOptions: ParticleOptions = {
        filter: ({x, y, image}) => {
            // Get pixel
            const pixel = image.get(x, y);
            // Make a particle for this pixel if blue > 50 (range 0-255)
            return pixel.b > 50;
        },
        color: ({x, y, image}) => props.color != null ? props.color : '#00fdfd',
        radius: () => Math.random() * 1.5 + 0.5,
        mass: () => 40,
        friction: () => 0.15,
        initialPosition: ({canvasDimensions}) => {
            return new Vector(canvasDimensions.width / 2, canvasDimensions.height / 2);
        }
    };

    const motionForce = (x: number, y: number): ParticleForce => {
        return forces.disturbance(x, y, 5);
    };

    const {innerWidth, innerHeight} = useWindowSize();

    const particles: number = props.maxParticles != null ? props.maxParticles : 4000;

    return (
        <ParticleImage
            src={props.url}
            width={props.width != null ? props.width : Number(innerWidth) - 9}
            height={props.height != null ? props.height : Number(innerHeight)}
            scale={props.scale != null ? Number(innerWidth) - 9 > 1000 ? props.scale : 0.20 : Number(innerWidth) - 9 > 1000 ? 0.35 : 0.20}
            entropy={props.entropy != null ? props.entropy : 20}
            maxParticles={Number(innerWidth) - 9 > 1000 ? particles : particles / 2}
            particleOptions={particleOptions}
            mouseMoveForce={motionForce}
            touchMoveForce={motionForce}
            backgroundColor={props.background != null ? props.background : 'transparent'}
        />
    );
}
export default Particle;