import express, { Request, Response } from 'express';
import helmet from 'helmet';
import cors from 'cors';
import morgan from 'morgan';
import rateLimit from 'express-rate-limit';
import swaggerJsdoc from 'swagger-jsdoc';
import swaggerUi from 'swagger-ui-express';
import config from './config';
import routes from './routes/index';
import errorHandler from './middleware/errorHandler';

const app = express();
const port = config.port;

app.use(helmet());
// HTTP request logging
app.use(morgan('combined'));
app.use(cors({ origin: config.corsOrigin }));

// Rate limiter: 100 requests per 15 minutes per IP
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 100,
  standardHeaders: true,
  legacyHeaders: false,
});
app.use(limiter);

app.use(express.json());

app.get('/health', (_req: Request, res: Response) => res.json({ status: 'ok' }));

// Swagger / OpenAPI setup
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'FXStatic-BK API',
      version: '1.0.0',
      description: 'API documentation (OpenAPI)'
    },
    servers: [{ url: `http://localhost:${config.port}` }]
  },
  // Point to the API routes for JSDoc comments if you add them later
  apis: ['src/routes/**/*.ts', 'src/controllers/**/*.ts']
};

const swaggerSpec = swaggerJsdoc(swaggerOptions);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

app.use('/api', routes);

app.use(errorHandler);

app.listen(port, () => {
  console.log(`Server listening on port ${port}`);
});
