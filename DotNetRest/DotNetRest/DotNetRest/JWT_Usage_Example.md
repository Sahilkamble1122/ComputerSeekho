# JWT Authentication Usage Examples

## Frontend JavaScript/TypeScript Example

### 1. Login Function

```javascript
async function login(username, password) {
    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        if (response.ok) {
            const data = await response.json();
            
            // Store the token in localStorage
            localStorage.setItem('jwt_token', data.token);
            localStorage.setItem('user_role', data.role);
            localStorage.setItem('username', data.username);
            
            console.log('Login successful:', data);
            return data;
        } else {
            const error = await response.json();
            console.error('Login failed:', error.message);
            throw new Error(error.message);
        }
    } catch (error) {
        console.error('Login error:', error);
        throw error;
    }
}
```

### 2. Making Authenticated Requests

```javascript
async function fetchProtectedData(endpoint) {
    const token = localStorage.getItem('jwt_token');
    
    if (!token) {
        throw new Error('No authentication token found');
    }

    try {
        const response = await fetch(endpoint, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });

        if (response.status === 401) {
            // Token expired or invalid
            localStorage.removeItem('jwt_token');
            localStorage.removeItem('user_role');
            localStorage.removeItem('username');
            window.location.href = '/login';
            return;
        }

        if (response.ok) {
            return await response.json();
        } else {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
    } catch (error) {
        console.error('Request failed:', error);
        throw error;
    }
}
```

### 3. Example Usage

```javascript
// Login
login('admin', 'admin123')
    .then(userData => {
        console.log('Logged in as:', userData.username);
        console.log('Role:', userData.role);
        
        // Now fetch protected data
        return fetchProtectedData('/api/staffs');
    })
    .then(staffData => {
        console.log('Staff data:', staffData);
    })
    .catch(error => {
        console.error('Error:', error);
    });
```

## React Example

### 1. Authentication Context

```jsx
import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem('jwt_token'));

    const login = async (username, password) => {
        try {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password })
            });

            const data = await response.json();
            
            if (data.success) {
                localStorage.setItem('jwt_token', data.token);
                setToken(data.token);
                setUser({
                    username: data.username,
                    role: data.role
                });
                return data;
            } else {
                throw new Error(data.message);
            }
        } catch (error) {
            throw error;
        }
    };

    const logout = () => {
        localStorage.removeItem('jwt_token');
        setToken(null);
        setUser(null);
    };

    const isAuthenticated = () => {
        return token !== null;
    };

    const hasRole = (role) => {
        return user && user.role === role;
    };

    return (
        <AuthContext.Provider value={{ user, token, login, logout, isAuthenticated, hasRole }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};
```

### 2. Protected Route Component

```jsx
import React from 'react';
import { useAuth } from './AuthContext';

export const ProtectedRoute = ({ children, requiredRole = null }) => {
    const { isAuthenticated, hasRole } = useAuth();

    if (!isAuthenticated()) {
        return <div>Please log in to access this page.</div>;
    }

    if (requiredRole && !hasRole(requiredRole)) {
        return <div>You don't have permission to access this page.</div>;
    }

    return children;
};
```

### 3. Login Component

```jsx
import React, { useState } from 'react';
import { useAuth } from './AuthContext';

export const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            await login(username, password);
            // Redirect to dashboard or home page
        } catch (error) {
            setError(error.message);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Username:</label>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Password:</label>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
            </div>
            {error && <div style={{ color: 'red' }}>{error}</div>}
            <button type="submit">Login</button>
        </form>
    );
};
```

### 4. Using Protected Routes

```jsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from './AuthContext';
import { ProtectedRoute } from './ProtectedRoute';
import { Login } from './Login';
import { Dashboard } from './Dashboard';
import { AdminPanel } from './AdminPanel';

function App() {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route 
                        path="/dashboard" 
                        element={
                            <ProtectedRoute>
                                <Dashboard />
                            </ProtectedRoute>
                        } 
                    />
                    <Route 
                        path="/admin" 
                        element={
                            <ProtectedRoute requiredRole="Admin">
                                <AdminPanel />
                            </ProtectedRoute>
                        } 
                    />
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;
```

## Angular Example

### 1. Authentication Service

```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser') || '{}'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue() {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>('/api/auth/login', { username, password })
      .pipe(map(user => {
        // store user details and jwt token in local storage
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  isAuthenticated(): boolean {
    return this.currentUserValue && this.currentUserValue.token;
  }

  hasRole(role: string): boolean {
    return this.currentUserValue && this.currentUserValue.role === role;
  }
}
```

### 2. HTTP Interceptor for JWT

```typescript
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add auth header with jwt if user is logged in and request is to the api url
    const currentUser = this.authService.currentUserValue;
    if (currentUser && currentUser.token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${currentUser.token}`
        }
      });
    }

    return next.handle(request);
  }
}
```

## Testing with cURL

### 1. Login and Get Token

```bash
curl -X POST "https://localhost:7001/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 2. Use Token for Protected Endpoint

```bash
# Replace YOUR_JWT_TOKEN with the actual token from login response
curl -X GET "https://localhost:7001/api/staffs" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 3. Test Role-Based Access

```bash
# This should work for Admin users
curl -X POST "https://localhost:7001/api/staffs" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "staffName": "New Staff",
    "staffUsername": "newstaff",
    "staffPassword": "password123",
    "staffRole": "User"
  }'
```

## Security Best Practices

1. **Store tokens securely**: Use httpOnly cookies for production
2. **Implement token refresh**: Add refresh token mechanism
3. **Validate tokens on server**: Always validate tokens on the server side
4. **Use HTTPS**: Always use HTTPS in production
5. **Implement logout**: Clear tokens on logout
6. **Handle token expiration**: Redirect to login when token expires
7. **Rate limiting**: Implement rate limiting on login endpoints
